package com.zettayun.api.requestParamEntity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数据点", description = "插入数据点信息")
public class RequestValueSet implements Serializable {
	
	@ApiModelProperty(value = "数据点数据结构", required = true)
    private Set set;

	@ApiModelProperty(value = "如果存在日期点的数据，是否需要更新:0是不更新；1是更新", required = true)
    private Integer isReplace;

	@ApiModelProperty(value = "数列类型", required = true)
    private Integer setType;

    public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
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
