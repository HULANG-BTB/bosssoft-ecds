package com.bosssoft.ecds.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author liuke
 */
@Configuration
public class JsonConfig {
    private static final String CHINA_TIME = "Asia/Shanghai";
    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(CHINA_TIME);

    @Bean
    public MappingJackson2HttpMessageConverter configConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(DEFAULT_TIME_ZONE);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        return converter;
    }
}
