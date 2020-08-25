package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.encryption.Decrypt;
import com.bosssoft.ecds.entity.vo.RSAPublicKeyVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.EncryptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/19 9:24
 */
@RestController
@RequestMapping("/encryption")
@Api(value = "接口加密控制层")
public class EncryptionController {
    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/getRSAPublicKey")
    @ApiOperation(value = "获取后端公钥")
    public QueryResponseResult getRSAPublicKey() {
        return encryptionService.getRSAPublicKey();
    }

    @PostMapping("/addRSAPublicKey")
    @ApiOperation(value = "增加前端公钥")
    @Decrypt
    public ResponseResult addRSAPublicKey(@RequestBody RSAPublicKeyVO rsaPublicKeyVO) {
        return encryptionService.addRSAPublicKey(rsaPublicKeyVO);
    }
}
