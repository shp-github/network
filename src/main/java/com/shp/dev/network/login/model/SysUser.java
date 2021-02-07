package com.shp.dev.network.login.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shp.dev.network.common.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 用户信息
 * @CreateTime: 2020/6/30 22:19
 * @PackageName: com.shp.dev.network.common.bean
 * @ProjectName: network
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息")
public class SysUser {

    @ApiModelProperty("主键")
    @TableField(exist = false)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("客户端输入的验证码")
    @TableField(exist = false)
    private String code;

    @ApiModelProperty("服务端验证码")
    @TableField(exist = false)
    private String uuid;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("操作时间")
    private String operateDate = DateUtils.getDate();

    @ApiModelProperty("操作人")
    private String operateBy;

}

