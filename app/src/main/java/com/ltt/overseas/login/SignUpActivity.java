package com.ltt.overseas.login;

import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;

/**
 * Created by Administrator on 2018/1/17.
 */
public class SignUpActivity extends BaseActivity{
    ActionBar bar;
    @Override
    protected int bindLayoutID() {
        return R.layout.activity_sign_up;
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
        bar.setTitle("SIGN UP");
    }
}
