package com.tao.space.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 单位角色
 * </p>
 *
 * @author cboss
 * @since 2023-04-01
 */
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "单位角色")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty("1: 公用角色，2:：私有角色，3：云平台创建角色")
    private Short type;

    @ApiModelProperty("单位")
    private Long unitId;

    @ApiModelProperty("组织机构")
    private Long orgId;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("1: 是，2:否")
    private Short isDelete;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SysRole{" +
            "roleId = " + roleId +
            ", type = " + type +
            ", unitId = " + unitId +
            ", orgId = " + orgId +
            ", name = " + name +
            ", version = " + version +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDelete = " + isDelete +
        "}";
    }
}
