package com.example.demo.exception;

import com.example.demo.dict.BusinessMsgEnum;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-03 16:40
 **/
public class BusinessErrorException extends RuntimeException {

    private static final long serialVersionUID = -7480022450501760611L;

    /**
     * 异常码
     */
    private long code;
    /**
     * 异常提示信息
     */
    private String message;

    public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.getCode();
        this.message = businessMsgEnum.getMsg();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}