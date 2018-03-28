package com.zettayun.api.requestParamEntity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("批量的报表")
public class RequestReportSetBatches {
	@ApiModelProperty(value ="是否更新", required = true)
	private Integer isReplace;
	
	@ApiModelProperty(value = "批量报表", required = true)
	private List<RequestReportSet> valueRows;

	public Integer getIsReplace() {
		return isReplace;
	}

	public void setIsReplace(Integer isReplace) {
		this.isReplace = isReplace;
	}

	public List<RequestReportSet> getValueRows() {
		return valueRows;
	}

	public void setValueRows(List<RequestReportSet> valueRows) {
		this.valueRows = valueRows;
	}
	
	
}
