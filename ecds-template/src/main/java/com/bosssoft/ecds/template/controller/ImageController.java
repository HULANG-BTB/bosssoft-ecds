package com.bosssoft.ecds.template.controller;

import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import com.bosssoft.ecds.template.service.IImageService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Api(value = "图片生成相关控制器")
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {

    @Autowired
    IImageService imageService;

    /**
     * 返回一个图片样板 PNG
     *
     * @return Content-Type: image/png
     */
    @ApiOperation(value = "返回一个图片样板 PNG")
    @GetMapping(value = "/template", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] pictureTemplate() throws IOException {
        File file = ResourceUtils.getFile("classpath:templates/NontaxBill.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return bytes;
    }

    /**
     * 根据传入的票据信息实时渲染，返回图片
     *
     * @param billDTO 非税票据DTO
     * @return Content-Type: image/png
     */
    @ApiOperation("根据传入的票据信息实时渲染，返回PNG图片")
    @PostMapping(value = "/genImage", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateImage(@RequestBody NontaxBillDTO billDTO) {
        return imageService.generateImage(billDTO);
    }

    /**
     * 根据传入的票据信息生成图片，返回图片的地址
     * 根据票据代码和号码确定唯一，不会重复生成
     *
     * @param billDTO 非税票据DTO
     * @return 可以访问的图片url
     */
    @ApiOperation("根据传入的票据信息生成图片，返回图片的地址。图片储存在本地")
    @PostMapping("/getAddress")
    public String getImage(@RequestBody NontaxBillDTO billDTO, HttpServletRequest request) {

        // 裁剪得到根目录地址，例如：http://localhost:8080
        int l = request.getRequestURI().length();
        StringBuffer host = request.getRequestURL();
        host.delete(host.length() - l, host.length());

        String file = imageService.getImage(billDTO);
        return host + "/image/" + file;
    }

    /**
     * 根据传入的票据信息生成图片，返回图片的云空间地址
     * 根据票据代码和号码确定唯一，不会重复生成
     *
     * @param billDTO 非税票据DTO
     * @return 限时访问的图片url
     */
    @ApiOperation("根据传入的信息生成图片，返回图片的阿里云OSS地址")
    @PostMapping("/getRemoteAddress")
    public String getRemoteAddress(@RequestBody NontaxBillDTO billDTO) {
        return imageService.getRemoteImage(billDTO);
    }

    /**
     * 从本地文件中返回图片
     *
     * @param filename 图片文件名
     * @return Content-Type: image/png
     */
    @ApiOperation("从本地文件中返回图片")
    @GetMapping(value = "/output/{filename}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generatedImage(@PathVariable("filename") @ApiParam("8位代码+10位号码") String filename) {
        String filePath = "output/" + filename + ".png";
        File file = new File(filePath);
        if (file.exists()) {
            byte[] bytes = new byte[0];
            try (FileInputStream inputStream = new FileInputStream(file)) {
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytes;
        } else {
            return new byte[0];
        }
    }
}
