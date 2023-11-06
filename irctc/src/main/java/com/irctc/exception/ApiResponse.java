package com.irctc.exception;

public class ApiResponse {

    private String message;
    private boolean sucess;
    private int id;

    private String field;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public ApiResponse(String message, boolean sucess) {
        super();
        this.message = message;
        this.sucess = sucess;
    }

    public ApiResponse(String message, boolean sucess, int id) {
        this.message = message;
        this.sucess = sucess;
        this.id = id;
    }

    public ApiResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApiResponse(String message, boolean sucess, String field) {
        this.message = message;
        this.sucess = sucess;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
