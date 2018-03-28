package com.zettayun.api.requestParamEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "批量的数据点信息")
public class RequestValueSetBatches {
	
	@ApiModelProperty(value = "批量的数据点", required = true)
    private List<Set> rows;

	@ApiModelProperty(value = "是否更新", required = true)
    private Integer isReplace;

	@ApiModelProperty(value = "类型", required = true)
    private Integer setType;

    public List<Set> getRows() {
		return rows;
	}

	public void setRows(List<Set> rows) {
		this.rows = rows;
	}

	public Integer getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Integer isReplace) {
        this.isReplace = isReplace;
    }

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }
}
