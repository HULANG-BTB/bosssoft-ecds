package com.bosssoft.ecds.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * mybatis-plus代码生成器
 *
 * @author cheng
 * @Date 2020/8/10 10:44
 */
public class CodeBuilder {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        
        // 数据库表名
        String[] tableList = {
                "fbe_stock_in",
                "fbe_stock_in_change",
                "fbe_stock_in_item"
        };
        
        // 作者
        String author = "cheng";
        // 乐观锁字段
        String version = "f_version";
        // 逻辑删除字段
        String deleted = "f_logic_delete";
        // 数据库表前缀
        String tablePrefix = "fbe_";
        // 字段前缀
        String columnPrefix = "f_";
        // 包名
        String packageName = "com.bosssoft.nontax";
        
        // JDBC
        String dataBaseUrl = "jdbc:mysql://127.0.0.1:3306/branch?serverTimezone=UTC";
        String dataBaseDriverName = "com.mysql.cj.jdbc.Driver";
        String dataBaseUsername = "root";
        String dataBasePassword = "123456";
        
        // 自动填充配置
        // 根据自己的表结构修改
        // 创建时间
        TableFill createTime = new TableFill("f_create_time", FieldFill.INSERT);
        // 修改时间
        TableFill updateTime = new TableFill("f_update_time", FieldFill.INSERT_UPDATE);
        // 经办人ID
        // TableFill operatorId = new TableFill("f_operator_id", FieldFill.INSERT_UPDATE);
        // 经办人名字
        TableFill operator = new TableFill("f_author", FieldFill.INSERT_UPDATE);
        
        ArrayList<TableFill> list = new ArrayList<>();
        
        list.add(createTime);
        list.add(updateTime);
        //list.add(operatorId);
        list.add(operator);
        
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String path = System.getProperty("user.dir");
        System.out.println(path);
        globalConfig.setOutputDir(path + "/src/main/java");
        globalConfig.setAuthor(author);
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setControllerName("%sController");
        globalConfig.setEntityName("%sPO");
        globalConfig.setMapperName("%sDao");
        globalConfig.setFileOverride(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setActiveRecord(true);
        globalConfig.setSwagger2(true);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(globalConfig);
        
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dataBaseUrl);
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName(dataBaseDriverName);
        dataSourceConfig.setUsername(dataBaseUsername);
        dataSourceConfig.setPassword(dataBasePassword);
        autoGenerator.setDataSource(dataSourceConfig);
        
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setMapper("dao");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity.po");
        packageConfig.setService("service");
        packageConfig.setParent(packageName);
        autoGenerator.setPackageInfo(packageConfig);
        
        // 策略设置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableList);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntitySerialVersionUID(false);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setVersionFieldName(version);
        strategy.setLogicDeleteFieldName(deleted);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityColumnConstant(true);
        strategy.setChainModel(true);
        strategy.setTablePrefix(tablePrefix);
        strategy.setFieldPrefix(columnPrefix);
        
        
        strategy.setTableFillList(list);
        
        // RestFul 风格
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        
        autoGenerator.setStrategy(strategy);
        
        autoGenerator.execute();
    }
}
