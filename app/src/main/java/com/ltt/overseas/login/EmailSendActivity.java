package com.ltt.overseas.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ltt.overseas.MainActivity;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/17.
 */
public class EmailSendActivity extends BaseActivity {
    ActionBar bar;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_foget_password_second;
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
    @OnClick({R.id.tv_email_send, R.id.tv_need_help, R.id.btn_back_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_email_send:
                break;
            case R.id.tv_need_help:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.btn_back_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
