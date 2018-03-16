package com.zetta.fancier.getdata.common;

import java.io.Serializable;
import java.util.Date;

public class ErrorResult implements Serializable {
    /**
     * 描述 : id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 描述 : 异常时间
     */
    private Date date;

    /**
     * 描述 : 异常类名
     */
    private String type;

    /**
     * 描述 : 异常信息
     */
    private String message;

    /**
     * 描述 : 详细异常堆栈信息
     */
    private String stackTrace;

    /**
     * 描述 : 子异常
     */
    private RestResponse<String> child;

}

