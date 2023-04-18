package com.tao.space.service.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Author：cboss
 * @Date：2023/3/31
 */
@Data
public class LoginReq {
    @ApiModelProperty(value = "账号")
    @Size(max = 50, message = "username字段超过字符最大长度限制，最大长度为：50")
    private String username;

    @ApiModelProperty(value = "密码")
    @Size(max = 50, message = "password字段超过字符最大长度限制，最大长度为：50")
    private String password;
}
