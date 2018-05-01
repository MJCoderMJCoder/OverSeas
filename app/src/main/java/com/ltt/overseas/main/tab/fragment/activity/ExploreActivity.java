package com.ltt.overseas.main.tab.fragment.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.adapter.PopupAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.SectionListAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.TypeListAdapter;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.TypeBean;
import com.ltt.overseas.model.TypeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/25.
 */
public class ExploreActivity extends BaseActivity {
    @BindView(R.id.parent_lv)
    RecyclerView parentLv;
    @BindView(R.id.child_lv)
    RecyclerView childLv;
    ActionBar bar;
    private TypeListAdapter parentAdapter;
    private SectionListAdapter childAdapter;
    private List<String> parentList;
    private List<List<String>> chlidList;
    private List<String> childValues;

    private TypeBean mTypeBean;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_explore;
    }

    @Override
    protected void prepareActivity() {
        bar=ActionBar.init(this);
        bar.setTitle("Explore");
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
                    getSectionList();
                }
            }
        });
//        parentAdapter = new PopupAdapter(this,R.layout.popup_item,parentList,R.drawable.normal,R.drawable.press2);
//        childAdapter = new PopupAdapter(this,R.layout.popup_item,childValues,R.drawable.normal,R.drawable.press);
        childAdapter = new SectionListAdapter(this, R.drawable.normal, R.drawable.press);
        childAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {

            }
        });
        parentLv.setAdapter(parentAdapter);
        childLv.setAdapter(childAdapter);
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

    private void getSectionList(){
        showLoadingView();
        Call<PhoneListBean> call = RetrofitUtil.getAPIService().getCountryIds();
        call.enqueue(new CustomerCallBack<PhoneListBean>() {
            @Override
            public void onResponseResult(PhoneListBean response) {
                dismissLoadingView();
                childAdapter.addAll(response.getData());
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

}
