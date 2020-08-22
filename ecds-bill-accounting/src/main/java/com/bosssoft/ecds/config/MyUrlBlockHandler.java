package com.bosssoft.ecds.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.bosssoft.ecds.exception.ExceptionDetail;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @ClassName MyUrlBlockHandler
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/21 17:03
 * @Version 1.0
 */
@Component
@Slf4j(topic = "kafka_logger")
public class MyUrlBlockHandler implements UrlBlockHandler {

    /**
     * Handle the request when blocked.
     * @param request  Servlet request
     * @param response Servlet response
     * @param ex       the block exception.
     * @throws IOException some error occurs
     */
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        String massage = "Blocked by Sentinel: " + ex.getClass().getSimpleName() + ",time:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String tags = ex.getClass().getSimpleName();
        ExceptionDetail detail = new ExceptionDetail(CommonCode.SERVER_ERROR,massage,tags);
        log.error(JSON.toJSONString(detail));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(new ResponseResult(CommonCode.SERVER_ERROR)));
        out.flush();
        out.close();
    }
}
