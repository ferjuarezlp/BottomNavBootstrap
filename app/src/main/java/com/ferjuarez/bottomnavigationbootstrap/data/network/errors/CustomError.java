package com.ferjuarez.bottomnavigationbootstrap.data.network.errors;

import com.ferjuarez.bottomnavigationbootstrap.R;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CustomError {
    LOGIN(422, R.string.error_message_user_pass),
    AUTH(403, R.string.error_user_auth),
    UNKNOWN(100, R.string.error_message_generic);

    private static final Map<Integer, CustomError> mapOption = Collections.unmodifiableMap(initializeMapping());

    private static Map<Integer, CustomError> initializeMapping() {
        Map<Integer, CustomError> map = new HashMap<>();
        for (CustomError error : EnumSet.allOf(CustomError.class)){
            map.put(error.code, error);
        }
        return map;
    }

    private final int code;
    private final int messageId;

    CustomError(int code, int messageId){
        this.code = code;
        this.messageId = messageId;
    }

    public int code() {
        return code;
    }

    public int messageId() {
        return messageId;
    }

    public static CustomError detectError(int code) {
        CustomError error = mapOption.get(code);
        return error != null ? error : UNKNOWN;
    }
}