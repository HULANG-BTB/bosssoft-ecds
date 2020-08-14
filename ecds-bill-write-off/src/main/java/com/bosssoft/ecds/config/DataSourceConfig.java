package com.bosssoft.ecds.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Configuration
public class DataSourceConfig {
    /**
     * FINANCIAL 库连接配置前缀 (与 yml 中的连接信息前缀一致 , 下同)
     */
    private static final String FINANCIAL_DATASOURCE_PREFIX = "spring.datasource.druid.financial";
    /**
     * FINANCIAL 库连接Bean名字
     */
    public static final String FINANCIAL_DATASOURCE_BEAN_NAME = "financialDataSource";
    /**
     * UNIT 库中间库连接配置前缀
     */
    private static final String UNIT_DATASOURCE_PREFIX = "spring.datasource.druid.unit";
    /**
     * UNIT 库中间库连接Bean名字
     */
    public static final String UNIT_DATASOURCE_BEAN_NAME = "unitDataSource";


    @Primary
    @Bean(name = FINANCIAL_DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = FINANCIAL_DATASOURCE_PREFIX)
    public DruidDataSource financial() {
        return new DruidDataSource();
    }


    @Bean(name = UNIT_DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = UNIT_DATASOURCE_PREFIX)
    public DruidDataSource b2b() {
        return new DruidDataSource();
    }
}
