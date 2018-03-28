package com.zettayun.common;


import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

    /**
     * 描述 : id
     */
    private static final long serialVersionUID = 1L;


    /**
     * 描述 : 状态码(业务定义)
     */
    private Integer resultCode = 00000;

    /**
     * 描述 : 状态码描述(业务定义)
     */
    private String resultMsg = HttpStatus.OK.getReasonPhrase();

    /**
     * 描述 : 结果集(泛型)
     */
    private T result = null;

    /**
     * 描述：结果集共有多少行
     */
    private Long total;

    /**
     * 描述 : 构造函数
     */
    public RestResponse() {
        super();
    }

    /**
     * 描述 : 构造函数
     *
     * @param result 结果集(泛型)
     */
    public RestResponse(T result) {
        this.result = result;
    }

    /**
     * 描述 : 构造函数
     *
     * @param httpStatus http状态
     */
    public RestResponse(HttpStatus httpStatus) {
        this.resultCode = httpStatus.value();
        this.resultMsg = httpStatus.getReasonPhrase();
    }


    /**
     * 描述 : 构造函数
     *
     * @param resultCode    状态码(业务定义)
     * @param resultMsg 状态码描述(业务定义)
     */
    public RestResponse(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * 描述 : 构造函数
     *
     * @param resultCode    状态码(业务定义)
     * @param resultMsg 状态码描述(业务定义)
     * @param result  结果集(泛型)
     */
    public RestResponse(Integer resultCode, String resultMsg, T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

