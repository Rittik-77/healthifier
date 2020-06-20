package com.ricky.healthifier.utils.exception;

public class AppException extends Exception {

    private static final long serialVersionUID = -3757584377000540777L;

    private String uiMessage;
    private ExceptionLevel exceptionLevel;

    /**
     * By default, ExceptionLevel is set to ERROR
     */
    public AppException(String uiMessage) {
        super();
        this.uiMessage = uiMessage;
        this.exceptionLevel = ExceptionLevel.ERROR;
    }

    public AppException(String uiMessage, ExceptionLevel exceptionLevel) {
        super();
        this.uiMessage = uiMessage;
        this.exceptionLevel = exceptionLevel;
    }

    public String getUiMessage() {
        return uiMessage;
    }

    public void setUiMessage(String uiMessage) {
        this.uiMessage = uiMessage;
    }

    public ExceptionLevel getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(ExceptionLevel exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
