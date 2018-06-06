package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.dialog.GoEditProfitDialog;
import com.ltt.overseas.main.tab.fragment.dialog.ResponseQuestionDialog;
import com.ltt.overseas.model.CreateResponseBean;
import com.ltt.overseas.model.CreateResponseBodyBean;
import com.ltt.overseas.model.ExploreQuestionBean;
import com.ltt.overseas.model.ExploreQuestionListBean;
import com.ltt.overseas.model.ExploreResponseDataBean;
import com.ltt.overseas.model.ResponseBean;
import com.ltt.overseas.model.ResponseListBean;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ExploreDetailActivity extends BaseActivity {
    ActionBar bar;
    private String requestid;
    //>0 可以创建
    private String show_button_response="0";
    private LayoutInflater mlflater;
    @BindView(R.id.ly_listquest)
    LinearLayout lyRequestList;
    @BindView(R.id.btn_Response)
    Button btn_response;
    private String authorization = XApplication.globalUserBean.getAccess_token();
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
        mlflater = getLayoutInflater().from(ExploreDetailActivity.this);
        requestid = this.getIntent().getStringExtra("requestid");
        show_button_response = this.getIntent().getStringExtra("show_button_response");
        if (show_button_response.equals("0")){
            btn_response.setVisibility(View.GONE);
        }
        getRequestList();

    }
    @OnClick({R.id.iv_notify,R.id.btn_Response})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btn_Response:

               clickBtnResponse();
                break;
        }
    }
   private void clickBtnResponse(){
       // 1 创建response
       // 2 成功后调用conversaion
       showLoadingView();
       Call<CreateResponseBean> call = RetrofitUtil.getAPIService().postCreateResponse(new CreateResponseBodyBean(requestid),authorization);
       call.enqueue(new CustomerCallBack<CreateResponseBean>() {
           @Override
           public void onResponseResult(CreateResponseBean response) {
               dismissLoadingView();
               Intent intent = new Intent(getContext(),ChatsActivity.class);
               intent.putExtra("conversation_id",response.getConversation());
               startActivity(intent);
           }

           @Override
           public void onResponseError(BaseBean errorMsg, boolean isNetError) {
               dismissLoadingView();
           }
       });
   }
    private void getRequestList() {
        showLoadingView();
        Call<ExploreResponseDataBean> call = RetrofitUtil.getAPIService().getQuestions(requestid);
        call.enqueue(new CustomerCallBack<ExploreResponseDataBean>() {
            @Override
            public void onResponseResult(ExploreResponseDataBean response) {
                dismissLoadingView();
                for  (ExploreQuestionListBean reqeustData:response.getData()){
                    View requestView =  mlflater.inflate(R.layout.detailrequestlayout,null);
                    TextView requestTittle=requestView.findViewById(R.id.tv_requestittle);
                    requestTittle.setText(reqeustData.getQuestion_title());
                    TextView requestAnswer=requestView.findViewById(R.id.tv_requestanswer);
                    requestAnswer.setText(reqeustData.getQuestion_answer());
                    lyRequestList.addView(requestView);
                }
                
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }
}
