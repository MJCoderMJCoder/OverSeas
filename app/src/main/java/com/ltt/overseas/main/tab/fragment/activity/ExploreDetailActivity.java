package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.dialog.GoEditProfitDialog;
import com.ltt.overseas.main.tab.fragment.dialog.ResponseQuestionDialog;
import com.ltt.overseas.model.ExploreQuestionBean;
import com.ltt.overseas.model.ExploreResponseDataBean;

import butterknife.OnClick;
import retrofit2.Call;

public class ExploreDetailActivity extends BaseActivity {
    ActionBar bar;
    @Override
    protected int bindLayoutID() {
        return R.layout.activity_explore_detail;
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
        bar.setTitle("Enquiry");
        bar.showNotify();
    }

    @OnClick({R.id.iv_notify,R.id.btn_Response})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btn_Response:
                ResponseQuestionDialog dialog = new ResponseQuestionDialog(this,R.style.Prompt_dialog,0.9,0.7);
                dialog.show();
                break;
        }
    }

}
