package com.shp.dev.network.common.util.jpa.dao;

import com.shp.dev.network.common.util.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/18 14:52
 * @PackageName: com.shp.dev.network.common.util.jpa.dao
 * @ProjectName: network
 */

@Component
public interface UserDAO extends JpaRepository<User,Long> {
}