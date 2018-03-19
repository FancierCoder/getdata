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
@TableName("data_set_classify")
public class DataSetClassify extends Model<DataSetClassify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动增长
     */
    private Integer id;
    /**
     * 数列id
     */
    @TableField("set_id")
    private Integer setId;
    /**
     * 分类id
     */
    private Integer classify;
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

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
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
        return "DataSetClassify{" +
                ", id=" + id +
                ", setId=" + setId +
                ", classify=" + classify +
                ", status=" + status +
                ", remark=" + remark +
                "}";
    }
}
