package com.shp.dev.network.common.util.validation;

import lombok.Data;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/1/16 14:05
 * @PackageName: com.shp.dev.network.common.util.validation
 * @ProjectName: network
 */


@Data
public class Name {

    //必须包含a
    @IsName(prefix = "a")
    private String name;


}
