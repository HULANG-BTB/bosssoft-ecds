package com.bosssoft.ecds.filter.log;

import lombok.Data;
import org.springframework.http.HttpHeaders;

/**
 * @author :Raiz
 * @date :2020/8/17
 */
@Data
public class RequestInfo {
    String method;

    String params;

    String url;

    HttpHeaders header;

    String remoteAddress;

    Integer port;

    String infoType;

    String nickname;

    String authId;

    String message;

    String tid;
}
