package com.bosssoft.ecds.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 自用的代码生成器，修改结合网上代码
 * 来源:https://blog.csdn.net/weixin_45544465/article/details/107058960?%3E
 * [MybatisPlus3.3.2代码自动生成]
 *
 * 需要自定义的有：
 * 作者、包名、表名（以及模块名等） at 32-36（位置未必精确）
 * 数据库配置 at 82-88
 * mapper.xml位置 at 115
 * 此外，还包括表名头（tablePrefix）等配置
 */
public class MpGenerator {

    // 固定
    private static final String projectPath = System.getProperty("user.dir");
    private static final String outPutDir = projectPath + "/ecds-finan-stock-management/src/main/java";
    // 自定义，表名用","分隔
    private static final String author = "misheep";
    private static final String packageName = "com.bosssoft.ecds";
    private static final String moduleName = "";
    private static final String[] tableName = ("fab_finan_bill,fab_warehouse,fbe_stock_outnotice" +
            ",fbe_stock_outnotice_change,fbe_stock_outnotice_item").split(",");
    private static final String tablePrefix = "";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 设置全局配置
        mpg.setGlobalConfig(getGlobalConfig());
        // 设置数据源配置
        mpg.setDataSource(getDataSourceConfig());
        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);
        // 自定义配置
        mpg.setCfg(getInjectionConfig());
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        // 策略配置
        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outPutDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        //配置生成的实体类，mapper，service命名规则
        gc.setEntityName("%sPo");
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        // 是否覆盖文件，默认false不覆盖
        gc.setFileOverride(true);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }
    private static DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/nontax_wzzzc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        // dsc.setSchemaName("public");
//        String driverName = "com.mysql.jdbc.Driver";
        String driverName = "com.mysql.cj.jdbc.Driver";
        dsc.setDriverName(driverName);
        dsc.setUsername("root");
        dsc.setPassword("123456");
        return dsc;
    }

    private static PackageConfig getPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        // 包名称
        pc.setParent(packageName);
        // 模块名称
        pc.setModuleName(moduleName);
        return pc;
    }

    private static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/ecds-finan-stock-management/src/main/resources/mappers/" + tableInfo.getEntityName().replace("", "")
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static StrategyConfig getStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix + "_");
        return strategy;
    }

}