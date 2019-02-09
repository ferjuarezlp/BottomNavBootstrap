package com.ferjuarez.bottomnavigationbootstrap.ui.customviews.inputText;

import android.util.Patterns;
import com.ferjuarez.bottomnavigationbootstrap.R;

public class InputTextViewManager {
    private InputTextViewInterface handler;

    public void setHandler(InputTextViewInterface handler) {
        this.handler = handler;
    }

    public boolean checkEmptyAndLength(String text) throws InvalidInputException {
        checkEmpty(text);
        checkLength(text);
        return true;
    }

    public boolean checkEmptyAndEmail(String email) throws InvalidInputException {
        checkEmpty(email);
        checkEmail(email);
        return true;
    }

    public boolean checkMatch(String text, String anotherText) throws InvalidInputException {
        checkPasswordMatch(text, anotherText);
        return true;
    }

    protected void checkEmpty(String text) throws InvalidInputException {
        if (text.isEmpty())
            throw new InvalidInputException(handler.getStringToShow(R.string.empty));
    }

    protected void checkEmail(String email) throws InvalidInputException {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            throw new InvalidInputException(handler.getStringToShow(R.string.invalid_email));
    }

    protected void checkLength(String text) throws InvalidInputException {
        if (handler.isCounterEnabled() && handler.textIsGreaterThanMax(text))
            throw new InvalidInputException(handler.getStringToShow(R.string.invalid));
    }

    protected void checkPasswordMatch(String password, String passwordAgain) throws InvalidInputException {
        if (!password.equals(passwordAgain))
            throw new InvalidInputException(handler.getString(R.string.password_do_not_match));
    }
}