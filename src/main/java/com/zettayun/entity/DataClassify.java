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
@TableName("data_classify")
public class DataClassify extends Model<DataClassify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动增长
     */
    private Integer id;
    /**
     * 分类名
     */
    @TableField("class_name")
    private String className;
    /**
     * 父分类id，无父分类为0
     */
    @TableField("parent_class_id")
    private Integer parentClassId;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getParentClassId() {
        return parentClassId;
    }

    public void setParentClassId(Integer parentClassId) {
        this.parentClassId = parentClassId;
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
        return "DataClassify{" +
                ", id=" + id +
                ", className=" + className +
                ", parentClassId=" + parentClassId +
                ", status=" + status +
                ", remark=" + remark +
                "}";
    }
}
