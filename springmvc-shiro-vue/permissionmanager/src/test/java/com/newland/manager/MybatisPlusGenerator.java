package com.newland.manager;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.mysql.cj.jdbc.Driver;

public class MybatisPlusGenerator {
    public static void main(String[] args){
        GlobalConfig config=new GlobalConfig.Builder()
                .author("leellun")
                .outputDir("E:\\javaweb\\workspaceall\\javawebdemo\\shiro-manager\\permissionmanager\\src\\main\\java")
                .fileOverride()
                .build();

        DruidDataSource source=new DruidDataSource();
        try{
            source.setDriver(new Driver());
        }catch (Exception e){
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/permission_manager?autoReconnect=true&userUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&allowMultiQueries=true";
        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig.Builder(url,"leellun","liulun666")
                .build();
        //策略配置
        StrategyConfig stConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .addInclude("t_user","t_role","t_menu","t_role_menu","t_user_role")
                .addTablePrefix("t_")
                .build();

        PackageConfig pkConfig=new PackageConfig.Builder()
                .parent("com.newland.manager")
                .controller("controller")
                .entity("domain")
                .service("service")
                .mapper("mapper")
                .build();

        new AutoGenerator(dsConfig).global(config).strategy(stConfig).packageInfo(pkConfig).execute();
    }
}
