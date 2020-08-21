package com.bosssoft.ecds.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Configuration
@MapperScan(basePackages = "com.bosssoft.ecds.dao.financial", sqlSessionTemplateRef = "financialSqlSessionTemplate")
public class FinancialDataSourceConfig {

    @Autowired
    @Qualifier(DataSourceConfig.FINANCIAL_DATASOURCE_BEAN_NAME)
    private DataSource financial;

    @Primary
    @Bean("financialSqlSessionFactory")
    public SqlSessionFactory financialSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(financial);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.addInterceptor(new PaginationInterceptor());
        configuration.addInterceptor(new OptimisticLockerInterceptor());
        factoryBean.setConfiguration(configuration);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaObjectHandlerConfig());
        factoryBean.setGlobalConfig(globalConfig);

        return factoryBean.getObject();
    }


    @Primary
    @Bean("financialSqlSessionTemplate")
    public SqlSessionTemplate financialSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(financialSqlSessionFactory());
        return template;
    }
}
