package com.tao.space.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 新建新表请拷贝这个样例模板
 * </p>
 *
 * @author cboss
 * @since 2023-04-03
 */
@ApiModel(value = "Example对象", description = "新建新表请拷贝这个样例模板")
public class Example implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "example_id", type = IdType.AUTO)
    private Long exampleId;

    @ApiModelProperty("名称")
    private String name;

    private String type;

    private Byte tinyintA;

    private Short smallintA;

    private Integer intA;

    private Long bigintA;

    private Double floatA;

    private Object doubleA;

    private BigDecimal decimalA;

    private LocalDate dateA;

    private LocalDateTime datetimeA;

    @ApiModelProperty("记录创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("记录更新时间")
    private LocalDateTime updateTime;

    public Long getExampleId() {
        return exampleId;
    }

    public void setExampleId(Long exampleId) {
        this.exampleId = exampleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte getTinyintA() {
        return tinyintA;
    }

    public void setTinyintA(Byte tinyintA) {
        this.tinyintA = tinyintA;
    }

    public Short getSmallintA() {
        return smallintA;
    }

    public void setSmallintA(Short smallintA) {
        this.smallintA = smallintA;
    }

    public Integer getIntA() {
        return intA;
    }

    public void setIntA(Integer intA) {
        this.intA = intA;
    }

    public Long getBigintA() {
        return bigintA;
    }

    public void setBigintA(Long bigintA) {
        this.bigintA = bigintA;
    }

    public Double getFloatA() {
        return floatA;
    }

    public void setFloatA(Double floatA) {
        this.floatA = floatA;
    }

    public Object getDoubleA() {
        return doubleA;
    }

    public void setDoubleA(Object doubleA) {
        this.doubleA = doubleA;
    }

    public BigDecimal getDecimalA() {
        return decimalA;
    }

    public void setDecimalA(BigDecimal decimalA) {
        this.decimalA = decimalA;
    }

    public LocalDate getDateA() {
        return dateA;
    }

    public void setDateA(LocalDate dateA) {
        this.dateA = dateA;
    }

    public LocalDateTime getDatetimeA() {
        return datetimeA;
    }

    public void setDatetimeA(LocalDateTime datetimeA) {
        this.datetimeA = datetimeA;
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

    @Override
    public String toString() {
        return "Example{" +
            "exampleId = " + exampleId +
            ", name = " + name +
            ", type = " + type +
            ", tinyintA = " + tinyintA +
            ", smallintA = " + smallintA +
            ", intA = " + intA +
            ", bigintA = " + bigintA +
            ", floatA = " + floatA +
            ", doubleA = " + doubleA +
            ", decimalA = " + decimalA +
            ", dateA = " + dateA +
            ", datetimeA = " + datetimeA +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
