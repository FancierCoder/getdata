package com.zettayun.api.requestParamEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数列信息", description = "构建的数列的信息")
public class RequestDataSet {
	
	@ApiModelProperty(value = "数列名", required = true)
    private String dataSetName;

	@ApiModelProperty(value = "数据来源", required = true)
    private String dataSource;

	@ApiModelProperty(value = "统计方式:[“年”,”季度”,”月”,”日”···]", required = true)
    private String period;

	@ApiModelProperty(value = "单位", required = true)
    private String valueUnit;

	@ApiModelProperty(value = "集合类型", required = true)
    private Integer setType;

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }
}
