package com.shp.dev.network.common.util.jpa.service;

import com.shp.dev.network.common.util.jpa.model.User;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/18 14:53
 * @PackageName: com.shp.dev.network.common.util.jpa.service
 * @ProjectName: network
 */
public interface UserService {

    List<User> findAll();

}
