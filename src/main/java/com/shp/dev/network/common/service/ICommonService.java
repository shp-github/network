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

    public ResultBean opsForValueSet(String key, String value, Integer db);

    public ResultBean opsForValueGet(String key, Integer db);

    public ResultBean opsForListSet(String key, List list, Integer db);

    public ResultBean opsForListGet(String key, Integer db);

    public ResultBean opsForHashGet(String key, Integer db);

    public ResultBean opsForHashSet(String key, Map map, Integer db);

    public ResultBean toBase64(MultipartFile file);

    public ResultBean upload(MultipartFile file, String fileName, String frist, String last);

    public ResultBean code();

    public ResultBean uploadMinio(String fileName, String objectName);

    public ResultBean updateService(MultipartFile file);

    public void to(HttpServletResponse res);
}
