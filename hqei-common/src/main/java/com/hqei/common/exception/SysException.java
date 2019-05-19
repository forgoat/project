package com.hqei.common.exception;

public class SysException extends Exception {
    private int errorCode;
    private Throwable cause ;

    public SysException(String message) {
        super(message);
    }

    public SysException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
    }

    public SysException(int errorCode, String message, Throwable cause) {
        this(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
