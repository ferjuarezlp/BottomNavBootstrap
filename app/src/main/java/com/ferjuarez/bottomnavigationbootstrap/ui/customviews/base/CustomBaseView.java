package com.ferjuarez.bottomnavigationbootstrap.ui.customviews.base;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class CustomBaseView extends LinearLayout{

    public CustomBaseView(Context context) {
        super(context);
    }

    public CustomBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void setUp(Context context, @Nullable AttributeSet attrs);

    protected View inflateView(Context context, int layout, ViewGroup root){
        return LayoutInflater.from(context).inflate(layout, root, true);
    }

    protected InputFilter[] createFilter(int max){
        return new InputFilter[]{new InputFilter.LengthFilter(max)};
    }

    protected void log(String tag, String message){
        Log.e(tag, message);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
}