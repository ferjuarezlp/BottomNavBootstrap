package com.ferjuarez.bottomnavigationbootstrap.ui.customviews.inputText;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.ferjuarez.bottomnavigationbootstrap.BottomNavigationApp;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.customviews.base.CustomBaseView;
import com.ferjuarez.bottomnavigationbootstrap.utils.DecimalDigitsInputFilter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import javax.inject.Inject;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class InputTextView extends CustomBaseView implements InputTextViewInterface {
    private static final int MIN_TEXT_LINE = 1;

    @Inject InputTextViewManager manager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @BindView(R.id.edit_text) EditText editText;

    @BindColor(R.color.colorAccent) int colorAccent;

    public InputTextView(Context context) {
        super(context);
        setUp(context, null);
    }

    public InputTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    @Override
    public void setUp(Context context, @Nullable AttributeSet attrs) {
        ButterKnife.bind(this, inflateView(context, R.layout.view_input_text, this));
        BottomNavigationApp.getAppComponent().inject(this);
        manager.setHandler(this);
        RxTextView.textChanges(editText)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> textInputLayout.setError(null));
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InputTextView);

        boolean editable = typedArray.getBoolean(R.styleable.InputTextView_input_editable, true);
        setEditable(editable);

        boolean errorEnabled = typedArray.getBoolean(R.styleable.InputTextView_errorEnabled, false);
        setErrorEnabled(errorEnabled);

        boolean maxLengthEnabled = typedArray.getBoolean(R.styleable.InputTextView_maxLengthEnabled, false);
        setMaxLengthEnabled(maxLengthEnabled);

        int maxTextLength = typedArray.getInteger(R.styleable.InputTextView_max_text_length, 0);
        setTextMaxLength(maxTextLength);

        int inputType = typedArray.getInt(R.styleable.InputTextView_android_inputType, InputType.TYPE_NULL);
        setInputType(inputType);

        int lines = typedArray.getInt(R.styleable.InputTextView_android_lines, 1);
        setLines(lines);

        String text = typedArray.getString(R.styleable.InputTextView_android_text);
        setText(text);

        String hint = typedArray.getString(R.styleable.InputTextView_hint);
        setHint(hint);

        boolean enabled = typedArray.getBoolean(R.styleable.InputTextView_android_enabled, true);
        setEnabled(enabled);

        int gravity = typedArray.getInt(R.styleable.InputTextView_android_gravity, Gravity.START);
        setGravityEditText(gravity);

        int textColor = typedArray.getColor(R.styleable.InputTextView_android_textColor, 0);
        setTextColor(textColor);

        int textColorHint = typedArray.getColor(R.styleable.InputTextView_android_textColorHint, 0);
        setTextColorHint(textColorHint);

        //customizeFont();

        typedArray.recycle();
    }

    public void doDispose() {
        compositeDisposable.dispose();
    }

    /**
     * Getters & Setters
     */
    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        if (text != null)
            editText.setText(text);
    }

    public void setHint(String text) {
        if (text != null)
            textInputLayout.setHint(text);
    }

    public void setInputType(int inputType) {
        editText.setInputType(inputType);
    }

    public void setEditable(boolean editable) {
        if(!editable){
            editText.setKeyListener(null);
            editText.setFocusable(false);
            editText.setClickable(true);
        }
    }

    public void setErrorEnabled(boolean enabled) {
        textInputLayout.setErrorEnabled(enabled);
    }

    public void setMaxLengthEnabled(boolean enabled) {
        textInputLayout.setCounterEnabled(enabled);
    }

    public void setTextMaxLength(int maxLength) {
        textInputLayout.setCounterMaxLength(maxLength);
        if (maxLength > 0)
            editText.setFilters(createFilter(maxLength));
    }

    public void setColor(int color) { editText.setTextColor(color); }

    public void setLines(int lines) {
        if (lines >= MIN_TEXT_LINE)
            setLinesAttributes(lines);
    }

    private void setLinesAttributes(int lines) {
        editText.setLines(lines);
        editText.setGravity(Gravity.START | Gravity.TOP);
    }

    public void setGravityEditText(int gravity){
        editText.setGravity(gravity);
        textInputLayout.setGravity(gravity);
    }

    public void setTextColor(int color){
        if (color > 0)
            editText.setTextColor(color);

    }

    public void setTextColorHint(int color){
        if (color > 0)
            editText.setHintTextColor(color);
    }

    public void setInputFilter(Integer beforeZero, Integer afterZero){
        editText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(beforeZero,afterZero)});
    }

    public void addOnTextChangeListener(TextWatcher textWatcher){
        editText.addTextChangedListener(textWatcher);
    }

    @Override
    public void setEnabled(boolean value) {
        editText.setEnabled(value);
    }

    @Override
    public void setFocusable(boolean focusable) {
        editText.setFocusable(focusable);
    }

    public void clearField() {
        editText.getText().clear();
    }

    public void setError(@NonNull String error) {
        textInputLayout.setError(error);
    }

    /**
     * Public Checks
     */
    public boolean isTextValid() {
        try {
            return manager.checkEmptyAndLength(editText.getText().toString().trim());
        } catch (InvalidInputException exception) {
            return manageErrorAndReturn(exception);
        }
    }

    public boolean isPasswordValid() {
        try {
            return manager.checkEmptyAndLength(editText.getText().toString().trim());
        } catch (InvalidInputException exception) {
            clearField();
            return manageErrorAndReturn(exception);
        }
    }

    public boolean isEmailValid() {
        try {
            return manager.checkEmptyAndEmail(editText.getText().toString().trim());
        } catch (InvalidInputException exception) {
            return manageErrorAndReturn(exception);
        }
    }

    public int getSelectionStart(){
        return editText.getSelectionStart();
    }

    public void setSelection(int selection){
        editText.setSelection(selection);
    }

    public void showKeyboard(){
        InputMethodManager manager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public boolean manageErrorAndReturn(InvalidInputException exception) {
        setError(exception.getMessage());
        return false;
    }

    @Override
    public boolean textIsGreaterThanMax(String text) {
        return text.length() > textInputLayout.getCounterMaxLength();
    }

    @Override
    public String getString(int stringId) {
        return getResources().getString(stringId);
    }

    @Override
    public String getStringToShow(int resourceId) {
        return String.format("%s", getResources().getString(resourceId));
    }

    private String getHint(){
        if (textInputLayout.getHint() != null)
            return textInputLayout.getHint().toString();
        return  getResources().getString(R.string.field);
    }

    @Override
    public boolean isCounterEnabled() {
        return textInputLayout.isCounterEnabled();
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        editText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener){
        editText.setOnFocusChangeListener(onFocusChangeListener);
    }

}