package com.shp.dev.network.common.util.jpa.service;

import com.shp.dev.network.common.util.jpa.dao.UserDAO;
import com.shp.dev.network.common.util.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/18 14:54
 * @PackageName: com.shp.dev.network.common.util.jpa.service
 * @ProjectName: network
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        // 这里我们就可以直接使用 findAll 方法
        return userDAO.findAll();
    }
}
