package com.shp.dev.network.common.util.sql.service;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.util.sql.model.SysSql;
import org.springframework.web.multipart.MultipartFile;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/25 15:18
 * @PackageName: com.shp.dev.network8.0.sql.service
 * @ProjectName: network
 */
public interface ISysSqlService {

    public ResultBean select(SysSql parm);

    public ResultBean update(SysSql parm);

    public ResultBean uploadFile(MultipartFile file);
}
