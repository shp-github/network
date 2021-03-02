package com.shp.dev.network.service;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.model.SysWrite;

import javax.servlet.http.HttpServletResponse;

public interface IWriteService {

    void getWrite(HttpServletResponse res, SysWrite model);

    ResultBean getWriteList(SysWrite model, Tablepar tablepar);

    ResultBean delWrite(SysWrite model);

    ResultBean insert(SysWrite model);
}
