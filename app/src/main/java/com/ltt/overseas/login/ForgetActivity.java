package com.ltt.overseas.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ltt.overseas.MainActivity;
import com.ltt.overseas.R;
import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.utils.PreferencesUtils;
import com.ltt.overseas.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/17.
 */
public class ForgetActivity extends BaseActivity {
    ActionBar bar;
    @BindView(R.id.et_email)
    EditText etEmail;

    private String mEmail;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_foget_password;
    }

    @Override
    protected void prepareActivity() {
        bar = ActionBar.init(this);
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bar.setTitle("FORGET PASSWORD");
    }

    @OnClick({R.id.btn_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_forget:
                if(judgeInput()){
                    forgetPwd();
                }
                break;
        }
    }

    private boolean judgeInput(){
        boolean canSendEmail = true;
        mEmail = etEmail.getText().toString();
        if (mEmail.trim().isEmpty()){
            ToastUtils.showToast("Email Can Not Be Empty");
            canSendEmail = false;
        }
        return canSendEmail;
    }

    private void forgetPwd(){
        showLoadingView();
        UserBean userParams = new UserBean();
        userParams.setEmail(mEmail);
        Call<String> loginCall = RetrofitUtil.getAPIService().forgetPwd(userParams);
        loginCall.enqueue(new CustomerCallBack<String>() {
            @Override
            public void onResponseResult(String response) {
                startActivity(new Intent(ForgetActivity.this, EmailSendActivity.class));
                finish();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }
}
