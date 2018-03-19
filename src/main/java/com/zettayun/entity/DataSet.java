package com.zettayun.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 李铎
 * @since 2018-03-14
 */
@TableName("data_set")
public class DataSet extends Model<DataSet> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 数列名
     */
    @TableField("set_name")
    private String setName;
    /**
     * 数列下点的数量
     */
    @TableField("point_number")
    private Integer pointNumber;
    /**
     * 数据来源
     */
    @TableField("data_source")
    private String dataSource;
    /**
     * 数列创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 最后一次数据更新时间
     */
    @TableField("last_update_time")
    private Date lastUpdateTime;
    /**
     * 最后一次插入数据时间
     */
    @TableField("last_insert_time")
    private Date lastInsertTime;
    private String token;
    /**
     * 统计方式
     */
    private String period;
    /**
     * 状态，0为未启用，1为启用
     */
    private Integer status;
    /**
     * 值的单位
     */
    @TableField("value_unit")
    private String valueUnit;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1为数列，2为多行多列
     */
    @TableField("set_type")
    private Integer setType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Integer getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(Integer pointNumber) {
        this.pointNumber = pointNumber;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastInsertTime() {
        return lastInsertTime;
    }

    public void setLastInsertTime(Date lastInsertTime) {
        this.lastInsertTime = lastInsertTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                ", id=" + id +
                ", setName=" + setName +
                ", pointNumber=" + pointNumber +
                ", dataSource=" + dataSource +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", lastInsertTime=" + lastInsertTime +
                ", token=" + token +
                ", period=" + period +
                ", status=" + status +
                ", valueUnit=" + valueUnit +
                ", remark=" + remark +
                ", setType=" + setType +
                "}";
    }
}
