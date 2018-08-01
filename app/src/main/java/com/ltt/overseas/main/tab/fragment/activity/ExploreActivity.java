package com.ltt.overseas.main.tab.fragment.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.ExploreFragment;
import com.ltt.overseas.main.tab.fragment.adapter.PopupAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.SectionListAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.TypeListAdapter;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.SectionBean;
import com.ltt.overseas.model.SectionListBean;
import com.ltt.overseas.model.TypeBean;
import com.ltt.overseas.model.TypeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/25.
 */
public class ExploreActivity extends BaseActivity {
    @BindView(R.id.parent_lv)
    RecyclerView parentLv;
    @BindView(R.id.child_lv)
    RecyclerView childLv;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.golisting)
    Button btn_golisting;
    ActionBar bar;
    private TypeListAdapter parentAdapter;
    private SectionListAdapter childAdapter;
    private List<String> parentList;
    private List<List<String>> chlidList;
    private List<String> childValues;
    public final int REPLY_TYPE=1;
    private TypeBean mTypeBean;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_explore;
    }

    @Override
    protected void prepareActivity() {
        bar=ActionBar.init(this);
        bar.setTitle("Request Centre");
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bar.showNotify();
        initData();
        parentAdapter = new TypeListAdapter(this, R.drawable.normal,R.drawable.press2);
        parentAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                if (!mTypeBean.getType_id().equals(((TypeBean) object).getType_id())){
                    mTypeBean =(TypeBean) object;
                    getSectionList();
                }
            }
        });
        childAdapter = new SectionListAdapter(this, R.drawable.normal, R.drawable.press);
        childAdapter.setBtn_search(btn_search);
        childAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
               int   a=position;
            }
        });
        LinearLayoutManager parentManager = new LinearLayoutManager(this );
        parentLv.setLayoutManager(parentManager);
        parentLv.setAdapter(parentAdapter);
        LinearLayoutManager childManager = new LinearLayoutManager(this );
        childLv.setLayoutManager(childManager);
        childLv.setAdapter(childAdapter);
        btn_search.setVisibility(View.GONE);

    }

    public void initData(){
        getTypeList();
    }

    private void getTypeList(){
        showLoadingView();
        Call<TypeListBean> call = RetrofitUtil.getAPIService().getTypeList();
        call.enqueue(new CustomerCallBack<TypeListBean>() {
            @Override
            public void onResponseResult(TypeListBean response) {
                mTypeBean = response.getData().get(0);
                parentAdapter.addAll(response.getData());
                getSectionList();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private void getSectionList() {
        showLoadingView();
        Call<SectionListBean> call = RetrofitUtil.getAPIService().getSectionList(mTypeBean.getType_id());
        call.enqueue(new CustomerCallBack<SectionListBean>() {
            @Override
            public void onResponseResult(SectionListBean response) {
                dismissLoadingView();
                childAdapter.addAll(response.getData().get(0).getSection_list());
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }
    @OnClick({R.id.btn_search,R.id.golisting})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_search:
                Intent intent = new Intent("tellexfragment");
                intent.putExtra("sectionlist", childAdapter.getSectionList());
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                finish();
            case R.id.golisting:
                Intent intentweb = new Intent(this,WebviewActivity.class);
                intentweb.putExtra("weburl","https://popmach.com/buy?parent_id=6&sort=newest&page=1");
                startActivity(intentweb);
                break;

        }
    }



}
