package com.shp.dev.network.common.util.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/18 14:47
 * @PackageName: com.shp.dev.network.common.util.jpa.model
 * @ProjectName: network
 */

@Data
@Entity  // 该注解声明一个实体类，与数据库中的表对应
public class User {

    @Id   // 表明id
    @GeneratedValue   //  自动生成
    private Long id ;

    private String name ;
}