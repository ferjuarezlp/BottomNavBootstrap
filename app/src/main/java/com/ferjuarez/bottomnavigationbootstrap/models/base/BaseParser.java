package com.ferjuarez.bottomnavigationbootstrap.models.base;

public class BaseParser {

    protected String getValueStringSecure(String value){
        return value != null ? value : "";
    }

    protected Integer getValueNumberSecure(Integer value){
        return value != null ? value : 0;
    }

    protected float getValueFloatSecure(String value){
        return value != null ? Float.valueOf(value) : 0f;
    }
}