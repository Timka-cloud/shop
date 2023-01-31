package kz.timka.shop.exceptions;

public class AppError {
    private int code;

    private String message;

    public AppError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppError() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
