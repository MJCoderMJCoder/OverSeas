package com.ltt.overseas.main.tab.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.BaseFragment;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.activity.ExploreActivity;
import com.ltt.overseas.main.tab.fragment.activity.ExploreDetailActivity;
import com.ltt.overseas.main.tab.fragment.activity.NotificationActivity;
import com.ltt.overseas.main.tab.fragment.adapter.ExploreAdapter;
import com.ltt.overseas.model.ExploreQuestionBean;
import com.ltt.overseas.model.ExploreQuestionListBean;
import com.ltt.overseas.model.ExploreResponseDataBean;
import com.ltt.overseas.model.List_request_centerDataBean;
import com.ltt.overseas.model.List_request_centreBean;
import com.ltt.overseas.model.SectionBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/18.
 */
public class ExploreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.container)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.action_bar)
    View actionBar;

    private ExploreAdapter adapter;
    ActionBar bar;
    private String mSectionList="";
    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void prepareFragment() {
        bar = ActionBar.init(actionBar);
        bar.setBackground(R.mipmap.bg_title_popmach);
        bar.showNotify();
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExploreAdapter();
        recyclerView.setAdapter(adapter);String sectionlist;
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                adapter.setSelected(position);
                Intent intent = new Intent(getContext(), ExploreDetailActivity.class);
                intent.putExtra("requestid", ((List_request_centreBean )object).getRequest_id());
                intent.putExtra("show_button_response", ((List_request_centreBean )object).getShow_button_response());
                intent.putExtra("datacratetime", ((List_request_centreBean )object).getDate_created());
                intent.putExtra("username", ((List_request_centreBean )object).getRequester_name());
                startActivity(intent);
            }
        });
       broadRecieve();

    }
    //secionid1,sectionid2,sectionid3
    public  void setSectionList(String sectionList){
        mSectionList=sectionList;
    }
private void broadRecieve(){
    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("tellexfragment");
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mSectionList = intent.getStringExtra("sectionlist");
            getQuestionList();
        }

    };
    localBroadcastManager.registerReceiver(br, intentFilter);
    if (mSectionList.isEmpty())
        initQuestionList();
}
    @OnClick({R.id.iv_menu, R.id.iv_notify})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                startActivity(new Intent(getActivity(), ExploreActivity.class));
                break;
            case R.id.iv_notify:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
        }

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
    }
    public void getQuestionList() {
        showLoadingView();
        Call<List_request_centerDataBean> call = RetrofitUtil.getAPIService().getListRequestCentrebysection(mSectionList, XApplication.globalUserBean.getAccess_token());
        call.enqueue(new CustomerCallBack<List_request_centerDataBean>() {
            @Override
            public void onResponseResult(List_request_centerDataBean response) {
                dismissLoadingView();
                adapter.clear();
               adapter.addAll(response.getData());

            }

            @Override
            public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                dismissLoadingView();
            }

        });
    }
    public void initQuestionList() {
        showLoadingView();
        Call<List_request_centerDataBean> call = RetrofitUtil.getAPIService().getListRequestCentre(XApplication.globalUserBean.getAccess_token());
        call.enqueue(new CustomerCallBack<List_request_centerDataBean>() {
            @Override
            public void onResponseResult(List_request_centerDataBean response) {
                dismissLoadingView();
                adapter.clear();
                adapter.addAll(response.getData());

            }

            @Override
            public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                dismissLoadingView();
            }

        });
    }

}
