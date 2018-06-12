package com.ltt.overseas.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltt.overseas.R;

import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

/**
 * Created by lin on 2017/7/16.
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();

    private TagOperator operator = new TagOperator();
    private ObservableTransformer transformer = new RxTransformer(operator);
    private ProgressDialog loadingView;
    protected abstract int bindLayoutID();
    protected abstract void prepareFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayoutID(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareFragment();
    }

    @Override
    public void onDestroy() {
        dismissLoadingView();
        operator.onDestroy();
        super.onDestroy();
    }

    public ObservableTransformer transform() {
        return transformer;
    }

    protected void showLoadingView() {
        if (loadingView == null) {
            loadingView = new ProgressDialog(getContext());
            loadingView.setMessage(getString(R.string.loading_view_msg));
            loadingView.setCanceledOnTouchOutside(false);
            loadingView.setCancelable(false);
        }
        if (!loadingView.isShowing())
            loadingView.show();
    }

    protected void dismissLoadingView() {
        if (loadingView != null && loadingView.isShowing())
            loadingView.dismiss();
    }
}
