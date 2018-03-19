package com.zettayun.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */
@TableName("data_field")
public class DataField extends Model<DataField> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动增长
     */
    private Integer id;
    /**
     * 字段名
     */
    @TableField("field_name")
    private String fieldName;
    /**
     * 字段类型
     */
    @TableField("field_type")
    private String fieldType;
    /**
     * 状态，0为未启用，1为启用
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataField{" +
                ", id=" + id +
                ", fieldName=" + fieldName +
                ", fieldType=" + fieldType +
                ", status=" + status +
                ", remark=" + remark +
                "}";
    }
}
