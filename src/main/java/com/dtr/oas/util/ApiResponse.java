package com.dtr.oas.util;

/**
 * Created by safayat on 12/12/15.
 */
public class ApiResponse {

    public static final String OK = "ok";
    public static final String VALIDATION_ERROR = "validation_error";
    public static final String EXCEPTION = "exception";
    private Object data;
    private boolean success;
    private String message;
    private String type;

    public ApiResponse() {
        success = true;
        message = "";
        type = OK;
    }

    public Object getData() {
        return data;
    }

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }
    public boolean isFailed() {
        return !success;
    }

    public ApiResponse success(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse message(String message) {
        this.message = message;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ApiResponse type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
