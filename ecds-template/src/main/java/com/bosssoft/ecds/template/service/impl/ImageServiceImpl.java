package com.bosssoft.ecds.template.service.impl;

import com.bosssoft.ecds.template.dto.BillItemDTO;
import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.service.ImageService;
import com.bosssoft.ecds.template.util.AliyunOSSUtil;
import com.bosssoft.ecds.template.util.TextValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    AliyunOSSUtil ossUtil;

    @Override
    public byte[] generateImage(NontaxBillDTO billDTO) {
        List<TextValue> billValues = new ArrayList<>();
        billValues.add(new TextValue("billCode", 89, 60));
        billValues.add(new TextValue("serialCode", 631, 62));
        billValues.add(new TextValue("checkCode", 228, 60));
        billValues.add(new TextValue("payerName", 77, 85));
        billValues.add(new TextValue("date", 631, 85));
        billValues.add(new TextValue("totalAmount", 575, 281));
        billValues.add(new TextValue("totalAmountCapital", 171, 313));
        billValues.add(new TextValue("remark", 81, 341));
        billValues.add(new TextValue("addition", 97, 372));
        billValues.add(new TextValue("agenName", 162, 410));
        billValues.add(new TextValue("payee", 667, 410));
        billValues.add(new TextValue("checker", 410, 410));

        // 收费项目的二维坐标
        int[] itemX = {23, 112, 371, 419, 494, 575};
        int[] itemY = {156, 186, 218, 248};

        Map<String, TextValue> valueMap = new HashMap<>();
        for (TextValue billValue : billValues){
            String fieldName = billValue.getFieldName();

            // 给dto填充票据数据
            String value = getValueByField(billDTO, fieldName);
            billValue.setValue(value);

            valueMap.put(fieldName, billValue);
            log.info(billValue.toString());
        }

        // 填充收费项目的内容
        List<BillItemDTO> items = billDTO.getItems();
        // 图片里面最多只能有4个项目
        for (int i=0; i<items.size() && i<4; i++){
            BillItemDTO item = items.get(i);
            billValues.add(new TextValue("itemCode" + i, item.getItemCode(), itemX[0], itemY[i]));
            billValues.add(new TextValue("itemName" + i,item.getItemName(), itemX[1], itemY[i]));
            billValues.add(new TextValue("units" + i, item.getUnits(), itemX[2], itemY[i]));
            billValues.add(new TextValue("quantity" + i, item.getQuantity(), itemX[3], itemY[i]));
            billValues.add(new TextValue("standardName" + i, item.getStandardName(), itemX[4], itemY[i]));
            billValues.add(new TextValue("amount" + i, item.getAmount(), itemX[5], itemY[i]));
        }

        // 模板图片位置
        String srcImgName = "classpath:templates/NontaxBill.png";
        return addMark(billValues, srcImgName);
    }

    /**
     * 根据传入的票据信息生成图片，并返回图片的位置
     * 如果图片已经生成过，就直接返回地址
     * @param billDTO 非税票据DTO
     * @return 图片URL
     */
    @Override
    public String getImage(NontaxBillDTO billDTO) {
        String fileName = billDTO.getBillCode() + billDTO.getSerialCode() + ".png";
        String path = "output/" + fileName;
        File dir = new File("output");
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        File file = new File(path);

        // 如果文件不存在，就生成一个
        if (!file.exists()){
            log.info("{}文件不存在，生成。", fileName);
            byte[] bytes = generateImage(billDTO);
            try(FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return path;
    }

    @Override
    public String getRemoteImage(NontaxBillDTO billDTO) {
        String fileName = billDTO.getBillCode() + billDTO.getSerialCode() + ".png";
        String path = "boss-bill/" + fileName;

        // 检查OSS上是否存在文件，有就不生成
        if (!ossUtil.isExist(path)) {
            log.info("{}文件不存在，生成。", fileName);
            byte[] bytes = generateImage(billDTO);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

            // 上传文件到阿里云 OSS
            ossUtil.upload(path, inputStream);
        }

        // 5 分钟的图片访问时间
        URL url = ossUtil.temporaryUrl(path, 5 * 60 * 1000);

        return url.toString();
    }

    /**
     * 使用 Graphics2D 在图片上绘制文字
     * @param billValues 包含文字内容，坐标
     * @param srcImgName 可以带 `classpath:` 前缀的图片文件位置
     * @return 图片字节数组
     */
    private byte[] addMark(List<TextValue> billValues, String srcImgName){

        Font font = new Font("宋体", Font.PLAIN, 16);
        Color color = new Color(5, 0, 0, 255);

        File srcImgFile = null;
        try {
            srcImgFile = ResourceUtils.getFile(srcImgName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image srcImg = null;
        try {
            srcImg = ImageIO.read(srcImgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);

        // 绘制文本
        for (TextValue value : billValues){
            g.drawString(value.getValue(), value.getX(), value.getY());
        }

        g.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufImg, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return imgBytes;
    }

    /**
     * 通过反射获取票据对象的字段的值
     * @param billDTO
     * @return
     */
    private String getValueByField(NontaxBillDTO billDTO, String fieldName){
        Class stdClass = billDTO.getClass();
        String value = "";
        // 获取对象的字段
        try {
            Field priField = stdClass.getDeclaredField(fieldName);
            try {
                priField.setAccessible(true);
                value = (String)priField.get(billDTO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return value;
    }


}
