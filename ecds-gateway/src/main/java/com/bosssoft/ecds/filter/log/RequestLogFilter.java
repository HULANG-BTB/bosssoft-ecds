package com.bosssoft.ecds.filter.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author :Raiz
 * @date :2020/7/23
 */

@Component
@Slf4j(topic = "kafka_business_logger")
public class RequestLogFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_INFO = "request";
    private static final String RESPONSE_INFO = "response";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String nickname = exchange.getRequest().getHeaders().getFirst("auth_nickname");
        String authId = exchange.getRequest().getHeaders().getFirst("auth_id");
        ServerHttpRequest request = exchange.getRequest();
        RecorderServerHttpRequestDecorator requestDecorator = new RecorderServerHttpRequestDecorator(request);
        // 获取请求地址
        InetSocketAddress address = requestDecorator.getRemoteAddress();
        // 请求方法
        HttpMethod method = requestDecorator.getMethod();
        // 请求路径
        URI url = requestDecorator.getURI();
        // 头部信息
        HttpHeaders headers = requestDecorator.getHeaders();

        String requestParams = null;
        if (method.equals(HttpMethod.GET)) {
            MultiValueMap<String, String> map = request.getQueryParams();
            requestParams = JSON.toJSONString(map);
        }

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setMethod(method.name());
        requestInfo.setUrl(url.getPath());
        requestInfo.setRemoteAddress(address.getHostName());
        requestInfo.setPort(address.getPort());
        requestInfo.setHeader(headers);
        requestInfo.setParams(requestParams);
        requestInfo.setInfoType(REQUEST_INFO);
        requestInfo.setNickname(nickname);
        requestInfo.setAuthId(authId);


        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        // 记录response
                        ResponseInfo responseInfo = new ResponseInfo();
                        responseInfo.setResponse(new String(content, StandardCharsets.UTF_8));
                        responseInfo.setStatus(this.getStatusCode());
                        responseInfo.setHeader(this.getHeaders());
                        responseInfo.setInfoType(RESPONSE_INFO);
                        Flux<DataBuffer> param = requestDecorator.getBody();
                        if (!method.equals(HttpMethod.GET)) {
                            //读取requestBody传参
                            AtomicReference<String> requestBody = new AtomicReference<>("");
                            param.subscribe(buffer -> {
                                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
                                DataBufferUtils.release(buffer);
                                requestBody.set(charBuffer.toString());
                            });
                            requestInfo.setParams(requestBody.get());
                        }
                        // 记录request和response的日志
                        log.info(JSON.toJSONString(requestInfo));
                        log.info(JSON.toJSONString(responseInfo));

                        return bufferFactory.wrap(content);
                    }));
                }
                return super.writeWith(body); // if body is not a flux. never got there.
            }
        };

        return chain.filter(exchange.mutate().request(requestDecorator).response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return -2;
    }

}