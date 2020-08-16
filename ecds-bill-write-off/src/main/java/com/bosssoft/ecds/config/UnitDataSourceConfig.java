package com.bosssoft.ecds.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
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

import javax.sql.DataSource;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Configuration
@MapperScan(basePackages = "com.bosssoft.ecds.dao.unit", sqlSessionTemplateRef = "unitSqlSessionTemplate")
public class UnitDataSourceConfig {

    @Autowired
    @Qualifier(DataSourceConfig.UNIT_DATASOURCE_BEAN_NAME)
    private DataSource unit;

    @Bean("unitSqlSessionFactory")
    public SqlSessionFactory unitSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(unit);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.addInterceptor(new PaginationInterceptor());
        configuration.addInterceptor(new OptimisticLockerInterceptor());
        factoryBean.setConfiguration(configuration);

        return factoryBean.getObject();
    }

    @Bean("unitSqlSessionTemplate")
    public SqlSessionTemplate unitSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(unitSqlSessionFactory());
        return template;
    }
}
