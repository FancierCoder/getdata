package com.zettayun.requestParamEntity;

import java.io.Serializable;

public class RequestValueSet implements Serializable {
    private RequestValue requestValue;

    private Integer isReplace;

    private Integer setType;

    public RequestValue getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(RequestValue requestValue) {
        this.requestValue = requestValue;
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
