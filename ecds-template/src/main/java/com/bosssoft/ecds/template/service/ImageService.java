package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.entity.dto.NontaxBillDto;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 非税票据的图片生成服务
 *
 * @author 郭钰鸣
 */
@Service
public interface ImageService {
    /**
     * 根据票据信息实时生成图片
     *
     * @param billDTO 非税票据DTO
     * @return png图片文件字节数组
     */
    byte[] generateImage(NontaxBillDto billDTO);

    /**
     * 根据票据信息生成图片，返回图片本地url
     *
     * @param billDTO 非税票据DTO
     * @return png图片url
     */
    String getImage(NontaxBillDto billDTO);

    /**
     * 根据票据信息生成图片，返回图片在云空间的url
     *
     * @param billDTO
     * @return png图片url
     */
    String getRemoteImage(NontaxBillDto billDTO);

    /**
     * 获取模板图片的输入流
     *
     * @return 模板图片输入流
     */
    InputStream getTemplateFile();
}
