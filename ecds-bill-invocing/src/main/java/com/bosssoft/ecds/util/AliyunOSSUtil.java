package com.bosssoft.ecds.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AliyunOSSUtil {
    @Autowired
    AliyunOSSConfig ossConfig;

    public void setOssConfig(AliyunOSSConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    /**
     * 打印出桶内的所有文件
     */
    public void listFiles() {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();

        ObjectListing objectListing = ossClient.listObjects(bucketName);

        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            log.info(" - {} (size = {} )",
                    objectSummary.getKey(), objectSummary.getSize());
        }

        ossClient.shutdown();
    }

    /**
     * 从输入流上传
     *
     * @param objectName  对象路径
     * @param inputStream 输入流
     */
    public void upload(String objectName, InputStream inputStream) {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();
        ossClient.putObject(bucketName, objectName, inputStream);
        ossClient.shutdown();
    }

    /**
     * 下载对象到本地文件
     *
     * @param objectName 对象路径
     * @param file       文件
     */
    public void download(String objectName, File file) {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), file);
        ossClient.shutdown();
    }

    /**
     * 获取对象的下载链接，链接在指定时间内过期
     * @param objectName   对象路径
     * @param milliseconds 过期时间（毫秒），从请求时开始算起
     * @return 文件的 URL
     */
    public URL temporaryUrl(String objectName, long milliseconds) {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();

        Date expiration = new Date(new Date().getTime() + milliseconds);
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);

        ossClient.shutdown();
        return url;
    }

    /**
     * 判断对象是否存在
     * @param objectName 对象路径
     * @return 是否存在
     */
    public boolean isExist(String objectName) {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();

        boolean found = ossClient.doesObjectExist(bucketName, objectName);

        ossClient.shutdown();
        return found;
    }

    /**
     * 读取对象的文本打印在控制台
     *
     * @param objectName 对象路径
     */
    public void read(String objectName) {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();

        OSSObject ossObject = ossClient.getObject(bucketName, objectName);

        try (InputStream content = ossObject.getObjectContent()) {
            if (content != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ossClient.shutdown();
    }

    /**
     * 多线程下载对象到文件
     *
     * @param objectName 对象路径
     * @param file       文件
     * @param partSize   文件分片大小（byte）
     * @param nThreads   并行线程数量
     */
    public void concurrentUpload(String objectName, File file, final long partSize, int nThreads) throws Exception {
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();

        // 创建InitiateMultipartUploadRequest对象。
        InitiateMultipartUploadRequest request =
                new InitiateMultipartUploadRequest(bucketName, objectName);

        // 初始化分片。
        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个uploadId发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = upresult.getUploadId();

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = new ArrayList<PartETag>();
        final File sampleFile = file;
        long fileLength = sampleFile.length();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        // 遍历分片上传。
        for (int i = 0; i < partCount; i++) {
            log.info("{}/{}", i, partCount);
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = new FileInputStream(sampleFile);
            // 跳过已经上传的分片。
            instream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100 KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(i + 1);


            Runnable task = () -> {
                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                partETags.add(uploadPartResult.getPartETag());
                log.info("finished");
            };

            // 线程池提交并执行上传
            executorService.submit(task);
        }

        executorService.shutdown();
        // 等待线程池结束
        while (!executorService.isTerminated()) {
            executorService.awaitTermination(200, TimeUnit.MILLISECONDS);
        }

        // 创建CompleteMultipartUploadRequest对象。
        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

        // 完成上传
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);

        ossClient.shutdown();
    }

    /**
     * 断点续传下载
     *
     * @param objectName 对象位置
     * @param partSize   分片大小(byte)
     * @param taskNum    并发任务数量
     * @param fileName   下载文件名
     * @throws Throwable
     */
    public void resumableDownload(String objectName, final long partSize, int taskNum, String fileName) throws Throwable {
        // 创建OSSClient实例。
        OSS ossClient = ossConfig.getOssClient();
        String bucketName = ossConfig.getBucketName();
        // 下载请求，10个任务并发下载，启动断点续传。
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
        downloadFileRequest.setDownloadFile(fileName);
        downloadFileRequest.setPartSize(partSize);
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        downloadFileRequest.setCheckpointFile("checkPoint");
        // 下载文件。
        DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
        // 下载成功时，会返回文件元信息。
        downloadRes.getObjectMetadata();
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
