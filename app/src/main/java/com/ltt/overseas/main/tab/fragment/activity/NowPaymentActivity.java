package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.main.tab.fragment.dialog.FeedBackDialog;
import com.ltt.overseas.main.tab.fragment.dialog.PaySuccessDialog;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/26.
 */
public class NowPaymentActivity extends BaseActivity{
    ActionBar bar;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_paynow;
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
        bar.setTitle("Payment");
        bar.showNotify();
        PaySuccessDialog dialog=new PaySuccessDialog(this,R.style.Prompt_dialog,0.9,0.4);
        dialog.show();
    }


    @OnClick({R.id.iv_notify})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_notify:
                startActivity(new Intent(this,NotificationActivity.class));
                break;
        }
    }
}
