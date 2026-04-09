package com.example.miniproject01.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    protected void showLoading() {
        if (activity != null) {
            activity.showLoading();
        }
    }

    protected void hideLoading() {
        if (activity != null) {
            activity.hideLoading();
        }
    }

    protected void showToast(String message) {
        if (activity != null) {
            activity.showToast(message);
        }
    }

    protected void hideKeyboard() {
        if (activity != null) {
            activity.hideKeyboard();
        }
    }
}
