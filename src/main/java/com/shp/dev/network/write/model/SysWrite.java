package com.shp.dev.network.write.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shp.dev.network.common.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 向页面输出信息对应实体类
 * @CreateTime: 2020/7/25 23:03
 * @PackageName: com.shp.dev.network.write.model
 * @ProjectName: network
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysWrite {

    @TableField(exist = false)
    private Long id;
    private String keyword;
    private String description;
    private String body;
    private String uri;
    private String updateTime = DateUtils.getDate();
    private Integer isDel = 0;

}
