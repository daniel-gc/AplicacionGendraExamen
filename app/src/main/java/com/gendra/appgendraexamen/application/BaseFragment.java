package com.gendra.appgendraexamen.application;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gendra.appgendraexamen.utils.IBackHandler;

import static android.content.Context.INPUT_METHOD_SERVICE;

public abstract class BaseFragment extends Fragment {

    protected IBackHandler backHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        if (getActivity() instanceof IBackHandler) {
            backHandler = (IBackHandler) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        backHandler.setFragment(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void injectDependencies() {
    }

    protected abstract int getFragmentLayout();

    public boolean onBackPressed() {
        return false;
    }

    public void hideSoftKeyboard(Context context) {

        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

    }

}
