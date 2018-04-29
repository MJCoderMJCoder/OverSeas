package com.ltt.overseas.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.ltt.overseas.MainActivity;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/17.
 */
public class LoginActivity extends BaseActivity {
    ActionBar bar;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private String mEmail, mPwd;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_login;
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
        bar.setTitle("LOG IN");
    }

    @OnClick({R.id.tv_forget_pwd, R.id.iv_fblogin, R.id.iv_googlelogin, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.iv_fblogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.iv_googlelogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_login:
                getPhoneList();
//                startActivity(new Intent(this, MainActivity.class));
//                if(judgeInput()){
//                    login();
//                }
                break;

        }
    }

    private boolean judgeInput(){
        boolean canLogin = true;
        mEmail = etEmail.getText().toString();
        mPwd = etPwd.getText().toString();
        if (mEmail.trim().isEmpty()){
            ToastUtils.showToast("Email Can Not Be Empty");
            canLogin = false;
        }
        if (mPwd.trim().isEmpty()){
            ToastUtils.showToast("Password Can Not Be Empty");
            canLogin = false;
        }
        return canLogin;
    }

    private void login(){
        showLoadingView();
        UserBean userParams = new UserBean();
        userParams.setEmail(mEmail);
        userParams.setPassword(mPwd);
        Call<GsonUserBean> loginCall = RetrofitUtil.getAPIService().login(userParams);
        loginCall.enqueue(new CustomerCallBack<GsonUserBean>() {
            @Override
            public void onResponseResult(GsonUserBean response) {
                dismissLoadingView();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private void getPhoneList(){
        showLoadingView();
        Call<PhoneListBean> call = RetrofitUtil.getAPIService().getCountryIds();
        call.enqueue(new CustomerCallBack<PhoneListBean>() {
            @Override
            public void onResponseResult(PhoneListBean response) {
                dismissLoadingView();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

}
