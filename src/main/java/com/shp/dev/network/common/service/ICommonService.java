package com.shp.dev.network.common.service;

import com.shp.dev.network.common.bean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 共用业务类
 * @CreateTime: 2020/12/11 16:33
 * @PackageName: com.shp.dev.network.common.service
 * @ProjectName: network
 */

public interface ICommonService {

    ResultBean opsForValueSet(String key, String value, Integer db);

    ResultBean opsForValueGet(String key, Integer db);

    ResultBean opsForListSet(String key, List list, Integer db);

    ResultBean opsForListGet(String key, Integer db);

    ResultBean opsForHashGet(String key, Integer db);

    ResultBean opsForHashSet(String key, Map map, Integer db);

    ResultBean toBase64(MultipartFile file);

    ResultBean upload(MultipartFile file, String fileName, String frist, String last);

    ResultBean code();

    ResultBean uploadMinio(String fileName, String objectName);

    ResultBean updateService(MultipartFile file);

    void to(HttpServletResponse res);

    ResultBean update(MultipartFile file);

    //重启当前服务
    ResultBean restart();

}
