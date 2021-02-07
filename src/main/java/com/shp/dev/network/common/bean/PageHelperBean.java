package com.shp.dev.network.common.bean;

import com.github.pagehelper.PageHelper;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 封装PageHelper
 * @CreateTime: 2021/1/7 17:48
 * @PackageName: com.shp.dev.network.common.bean
 * @ProjectName: network
 */
public class PageHelperBean {
    public static void startPage(Tablepar tablepar) {
        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize(), true, null, null);
    }
}
