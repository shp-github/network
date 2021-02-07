package com.shp.dev.network.common.config;

import java.util.Arrays;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 定义常量
 * @CreateTime: 2020/12/22 9:38
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
public interface Constant {

    //redis配置
    String REDIS_HOST="47.92.213.36";
    Integer REDIS_PORT=6379;
    String REDIS_PASSWORD="2948299576";
    Integer REDIS_MAXIDL=100;
    Integer REDIS_MINIDL=1;
    Integer REDIS_TIMEOUT=60*1000;
    List<Integer> REDIS_DBS =Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    //mysql配置
    String MYSQL_USERNAME = "root";
    String MYSQL_PASSWORD = "root";
    String MYSQL_URL = "jdbc:mysql://47.92.213.36:3306/network?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";


}
