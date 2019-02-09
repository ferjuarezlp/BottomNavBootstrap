package com.ferjuarez.bottomnavigationbootstrap.ui.base;

import com.ferjuarez.bottomnavigationbootstrap.data.network.errors.CustomError;
import com.ferjuarez.bottomnavigationbootstrap.data.network.errors.ErrorResponse;

public abstract class BasePresenter {
    protected CustomError getCustomError(Throwable throwable) {
        if (!(throwable instanceof ErrorResponse)){
            return CustomError.UNKNOWN;
        } else {
            ErrorResponse response = ((ErrorResponse) throwable);
            return response.getError();
        }
    }
}