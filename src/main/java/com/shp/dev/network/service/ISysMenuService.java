package com.shp.dev.network.service;

import com.shp.dev.network.common.bean.ResultBean;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 按钮业务层
 * @CreateTime: 2020-07-06 16:06
 * @PackageName: com.shp.dev.network.menu.service
 * @ProjectName: network
 */
public interface ISysMenuService {

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 展示所有按钮
     * @CreateTime: 2020-07-06 18:16
     * @param:
     * @return: com.shp.dev.network.common.bean.ResultModel
     */
    ResultBean queryAllMenu();
}
