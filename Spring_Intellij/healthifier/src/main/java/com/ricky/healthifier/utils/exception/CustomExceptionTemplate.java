package com.ricky.healthifier.utils.exception;

public class CustomExceptionTemplate {

    private String uiMessage;
    private ExceptionLevel exceptionLevel;

    public CustomExceptionTemplate() {

    }

    public CustomExceptionTemplate(String uiMessage, ExceptionLevel exceptionLevel) {
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
}
