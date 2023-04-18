package com.tao.space.dal.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * 单位用户
 *
 * @author 江路
 **/
@Data
public class SysUser implements Serializable {
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

    @ApiModelProperty(value = "1:启用，2:停用",required = true)
    private Short status;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "创建时间",required = true)
    private Date createTime;

    @ApiModelProperty(value = "修改时间",required = true)
    private Date updateTime;

    @ApiModelProperty(value = "1: 是，2:否",required = true)
    private Short isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", unitId=").append(unitId);
        sb.append(", orgId=").append(orgId);
        sb.append(", realName=").append(realName);
        sb.append(", username=").append(username);
        sb.append(", salt=").append(salt);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}