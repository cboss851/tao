package com.tao.space.service.user.dto;

import com.tao.commons.result.RequestPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author：cboss
 * @Date：2023/3/26
 */
@Data
public class UserQueryListReq extends RequestPage {
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

    @ApiModelProperty(value = "手机",required = true)
    @Size(max = 15,message = "phone字段超过字符最大长度限制，最大长度为：15")
    private String phone;

    @ApiModelProperty(value = "1:启用，2:停用",required = true)
    private Short status;

    @ApiModelProperty(value = "创建时间",required = true)
    private Date createTime;

    @ApiModelProperty(value = "修改时间",required = true)
    private Date updateTime;
}
