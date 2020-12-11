package com.kubrick.annotation.utils.gen;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyBatisPlusGenerator
 * @description: 代码生成器
 * @date 2020/12/8 下午2:25
 */
public class MyBatisPlusGenerator {

    static List<String> busTableNames = new ArrayList<>();
    static List<String> proxyTableNames = new ArrayList<>();

    static {
        // bus table
        busTableNames.add("t_auth_detail_record");
        busTableNames.add("t_auth_record");
        busTableNames.add("t_client_login_record");
        busTableNames.add("t_config_charge");
        busTableNames.add("t_config_greet");
        busTableNames.add("t_domain");
        busTableNames.add("t_friend_extend_field");
        busTableNames.add("t_message_record");
        busTableNames.add("t_ol_pay_record");
        busTableNames.add("t_oss_up_record");
        busTableNames.add("t_reply");
        busTableNames.add("t_reply_group");
        busTableNames.add("t_req_process_record");
        busTableNames.add("t_tenant");
        busTableNames.add("t_tenant_account");
        busTableNames.add("t_tenant_amount_record");
        busTableNames.add("t_tenant_api_key");
        busTableNames.add("t_tenant_set_meal");
        busTableNames.add("t_tenant_translate_record");
        busTableNames.add("t_user");
        busTableNames.add("t_whats_account");
        busTableNames.add("t_whats_friend");
        busTableNames.add("t_whats_friend_status");

        // proxy table
        proxyTableNames.add("t_auth_detail_record");
        proxyTableNames.add("t_auth_record");
        proxyTableNames.add("t_notice");
        proxyTableNames.add("t_notice_user");
        proxyTableNames.add("t_req_process_record");
        proxyTableNames.add("t_tenant");
        proxyTableNames.add("t_tenant_service_url");
        proxyTableNames.add("t_tenant_user");
    }

    public static void main(String[] args) throws SQLException {
        String javaPath = "/Users/kubrick/Documents/workspace/java/study/my-annotation/code";
        String driverName = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://192.168.1.4:3306/scrm_bus";
        String url = "jdbc:mysql://192.168.1.4:3306/scrm_proxy";
        String dbName = "root";
        String dbPassword = "moppo123";
        //String packageName ="com.moppo.scrm.bus";
        String packageName ="com.moppo.scrm.proxy";

        String tableName = "t_notice";
        String[] tables = proxyTableNames.toArray(new String[proxyTableNames.size()]);
//        String[] tables = busTableNames.toArray(new String[busTableNames.size()]);
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setAuthor("k")
                .setOutputDir(javaPath)
                .setFileOverride(true)
                .setIdType(IdType.AUTO)
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setSwagger2(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUrl(url)
                .setUsername(dbName)
                .setPassword(dbPassword);

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig.setCapitalMode(true)
                // 指定表名 字段名是否使用下划线
                //.setsetDbColumnUnderline(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("t_")
                .setEntityLombokModel(true)
                .setInclude(tables);

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(packageName)
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity");
        //.setXml("mapper");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}
