package com.jt.jterp.model;

/**
 * Created by 王立强 on 2016/7/16.
 */
public class Error {

    /**
     * Success : false
     * Code : 4
     * Message : 密码错误
     */

    private boolean Success;
    private String Code;
    private String Message;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
