package com.bosssoft.ecds.generate;

import com.baomidou.mybatisplus.annotation.DbType;
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
 * @author: qiuheng
 * @create: 2020-08-12 09:11
 **/
public class MyGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局策略配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("/Users/qiuheng/IdeaProjects/bosssoft-ecds/ecds-bill-destory/src/main/java"); // 生成文件的输出目录,默认D根目录
        gc.setFileOverride(true); // 是否覆盖已有文件
        gc.setAuthor("qiuheng");
        gc.setOpen(false); // 是否打开输出目录,默认true
        gc.setEnableCache(false); // 是否在xml中添加二级缓存配置,默认false
        gc.setSwagger2(false); // 开启 swagger2 模式,默认false

        gc.setEntityName("%sPo");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                return super.processTypeConvert(fieldType);
//            }
//        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://39.97.123.56:3306/ecds_financial?useUnicode=true&characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 包名配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("taoism");
        pc.setParent("com.bosssoft.ecds");
        pc.setController("controller");
        pc.setEntity("po");
        pc.setMapper("dao");
        //pc.setXml("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return "/Users/qiuheng/IdeaProjects/bosssoft-ecds/ecds-bill-destory/src/main/resources/mapper"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[] { "user_" });// 此处可以修改为您的表前缀
        //strategy.setNaming(NamingStrategy.nochange);// 表名生成策略
        strategy.setInclude(new String[] {"fbe_destory_confirm"}); // 需要生成的表
        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        //strategy.setSuperEntityClass("com.shiyu.BaseEntity"); //自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityColumns(new String[] {"id","gmtCreate","gmtModified"});// 自定义基础的Entity类，公共字段
        strategy.setEntityLombokModel(true); // 是否为lombok模型
        strategy.setEntityBooleanColumnRemoveIsPrefix(true); // Boolean类型字段是否移除is前缀
        strategy.setRestControllerStyle(false); // 生成 @RestController 控制器，个人觉得有点多余
        //strategy.setSuperControllerClass("com.music.taosim.ant.common.BaseController");
        //startegy.setInclude("tableName");  当对某张表有所改动但只想重新生成这张表，可以这样设置

        strategy.setControllerMappingHyphenStyle(true); // 驼峰转连字符 如 umps_user 变为 upms/user
        // strategy.setTablePrefix(pc.getModuleName() + "_"); // 表前缀

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine()); //设置模板引擎类型，默认为 velocity
        mpg.execute();
    }

}