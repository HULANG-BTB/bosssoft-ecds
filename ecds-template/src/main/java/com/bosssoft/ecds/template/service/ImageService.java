package com.bosssoft.ecds.template.service;

import com.bosssoft.ecds.template.dto.NontaxBillDTO;
import org.springframework.stereotype.Service;

/**
 * 非税票据的图片生成服务
 * @author 郭钰鸣
 */
@Service
public interface ImageService {
    /**
     * 根据票据信息实时生成图片
     * @param billDTO 非税票据DTO
     * @return png图片文件字节数组
     */
    byte[] generateImage(NontaxBillDTO billDTO);

    /**
     * 根据票据信息生成图片，返回图片本地url
     * @param billDTO 非税票据DTO
     * @return png图片url
     */
    String getImage(NontaxBillDTO billDTO);

    /**
     * 根据票据信息生成图片，返回图片在云空间的url
     * @param billDTO
     * @return
     */
    String getRemoteImage(NontaxBillDTO billDTO);
}
