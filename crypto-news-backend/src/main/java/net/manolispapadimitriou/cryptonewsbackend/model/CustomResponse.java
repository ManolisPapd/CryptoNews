package net.manolispapadimitriou.cryptonewsbackend.model;

import java.sql.Timestamp;


public class CustomResponse {

    private String timestamp;
    private int status;
    private String message;

    public CustomResponse() {
    }

    public CustomResponse(int status, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.status = status;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
