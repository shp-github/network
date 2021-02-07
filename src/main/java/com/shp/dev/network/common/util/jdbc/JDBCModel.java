package com.shp.dev.network.common.util.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/12/31 1:04
 * @PackageName: com.shp.dev.network.common.util.jdbc
 * @ProjectName: network
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JDBCModel {

    private String sql;
    private Class<?> clz;
    private String url;
    private String user;
    private String password;


}
