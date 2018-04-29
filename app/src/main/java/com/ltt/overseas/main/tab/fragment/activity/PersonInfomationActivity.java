package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/24.
 */
public class PersonInfomationActivity extends BaseActivity {
    ActionBar bar;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_person_info;
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
        bar.setTitle("Personal Information");
        bar.showNotify();
    }

    @OnClick({R.id.tv_addr, R.id.btn_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addr:
                break;
            case R.id.btn_upload:
                startActivity(new Intent(this,VertificationActivity.class));
                finish();
                break;
        }
    }
}
