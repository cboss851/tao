package com.tao.space.service.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author：cboss
 * @Date：2023/3/26
 */
@Data
public class UserUpdateReq {
    @ApiModelProperty(value = "主键",required = true)
    private Long userId;

    @ApiModelProperty(value = "单位",required = true)
    private Long unitId;

    @ApiModelProperty(value = "组织机构",required = true)
    private Long orgId;

    @ApiModelProperty(value = "姓名")
    @Size(max = 50,message = "realName字段超过字符最大长度限制，最大长度为：50")
    private String realName;

    @ApiModelProperty(value = "账号，唯一")
    @Size(max = 50,message = "username字段超过字符最大长度限制，最大长度为：50")
    private String username;

    @ApiModelProperty(value = "密码撒盐")
    @Size(max = 20,message = "salt字段超过字符最大长度限制，最大长度为：20")
    private String salt;

    @ApiModelProperty(value = "密码，加密存储，不要明文")
    @Size(max = 50,message = "password字段超过字符最大长度限制，最大长度为：50")
    private String password;

    @ApiModelProperty(value = "手机",required = true)
    @Size(max = 15,message = "phone字段超过字符最大长度限制，最大长度为：15")
    @NotBlank(message = "phone-手机字段必填")
    private String phone;
}