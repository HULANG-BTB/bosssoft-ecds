package com.bosssoft.ecds.filter.log;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author :Raiz
 * @date :2020/8/17
 */
@Data
public class ResponseInfo {
    String response;

    HttpStatus status;

    HttpHeaders header;

    String infoType;

    String message;
}
