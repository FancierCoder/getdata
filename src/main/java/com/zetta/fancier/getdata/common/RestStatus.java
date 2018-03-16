package com.zetta.fancier.getdata.common;

public interface RestStatus {
    /**
     * the status codes of per restful request.
     *
     * @return 20xxx if succeed, 40xxx if client error, 50xxx if server side crash.
     */
    int code();

    /**
     * @return status enum name
     */
    String name();

    /**
     * @return 消息描述
     */
    String message();
}

