package com.ferjuarez.bottomnavigationbootstrap.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {

    protected abstract void setUp();

    protected void hideKeyBoard() {
        if (getActivity() == null)
            return;
        View view = getActivity().getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    protected void showMessageInToast(int messageId) {
        if (getContext() != null)
            Toast.makeText(getContext(), getText(messageId), Toast.LENGTH_SHORT).show();
    }

    protected void showMessageInToast(String message) {
        if (getContext() != null)
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void changeBackground(int resourceId){
        if (getActivity() != null)
            getActivity().getWindow().setBackgroundDrawableResource(resourceId);
    }

    protected void addMomentarilyFullDialog(int layoutId, DialogFragment dialogFragment){
        if (getActivity() == null || getActivity().getSupportFragmentManager() == null)
            return;
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(layoutId, dialogFragment)
                .addToBackStack(dialogFragment.getClass().getSimpleName())
                .commit();
    }

    protected void onBackPressed(){
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    protected void addTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setEnterTransition(new Slide(Gravity.END));
            setExitTransition(new Slide(Gravity.START));
        }
    }

    protected void showDialogConfirmation(String message, DialogInterface.OnClickListener listener) {
        showDialogConfirmation(message, android.R.string.ok, listener);
    }

    private void showDialogConfirmation(String message, int titlePositiveButton,
                                        DialogInterface.OnClickListener okListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(titlePositiveButton, okListener);
        builder.create().show();
    }

    protected void showErrorDialog(String message,
                                        DialogInterface.OnClickListener okListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    okListener.onClick(dialog, which);
                    dialog.dismiss();
                });
        builder.create().show();
    }

}