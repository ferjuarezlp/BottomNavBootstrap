package com.ferjuarez.bottomnavigationbootstrap.ui.customviews.inputText;

public interface InputTextViewInterface {
    String getString(int stringId);

    String getStringToShow(int resourceId);

    boolean isCounterEnabled();

    boolean textIsGreaterThanMax(String text);
}