package com.ferjuarez.bottomnavigationbootstrap.data.network.errors;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;

public class ErrorResponse extends IOException {
    private int code;
    private String message;

    public ErrorResponse(){
        super();
    }

    public ErrorResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(int code){
        this.code = code;
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

    public CustomError getError() {
        CustomError error = CustomError.detectError(code);
        return error;
    }

    public static ErrorResponse generateError(int code, String json) {
        try {
            Log.e(ErrorResponse.class.getSimpleName(), "Error detected: " + json);
            return !json.isEmpty() ? new Gson().fromJson(json, ErrorResponse.class) : new ErrorResponse();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new ErrorResponse(code);
        }
    }

    public static ErrorResponse generateError(String json) {
        try {
            Log.e(ErrorResponse.class.getSimpleName(), "Error detected: " + json);
            return !json.isEmpty() ? new Gson().fromJson(json, ErrorResponse.class) : new ErrorResponse();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new ErrorResponse();
        }
    }
}
