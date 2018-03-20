package com.zettayun.requestParamEntity;

import java.util.List;

public class RequestValueSetBatches {
    private List<RequestValue> valueRows;

    private Integer isReplace;

    private Integer setType;

    public List<RequestValue> getValueRows() {
        return valueRows;
    }

    public void setValueRows(List<RequestValue> valueRows) {
        this.valueRows = valueRows;
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
