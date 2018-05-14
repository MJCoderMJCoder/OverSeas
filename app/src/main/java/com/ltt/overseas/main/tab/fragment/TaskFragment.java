package com.ltt.overseas.main.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.BaseFragment;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.activity.MyRequestDetailActivity;
import com.ltt.overseas.main.tab.fragment.activity.NotificationActivity;
import com.ltt.overseas.main.tab.fragment.adapter.MyResponseAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.MyTaskAdapter;
import com.ltt.overseas.model.RequestBean;
import com.ltt.overseas.model.RequestListBean;
import com.ltt.overseas.model.ResponseListBean;
import com.ltt.overseas.model.TypeListBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.utils.L;
import com.ltt.overseas.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/18.
 * <p>
 * account:awesome@luqman.rocks
 * password:test123（R）
 * or password：luqman123（E）
 * <p>
 * 1. Response is for service provider
 * 2. Request is for user who answer the question and submit the request for service provider to response it
 * 3. Coins we don’t have for now and as our main website currently user can post unlimited request and with manual approval from our backend administrator
 * 4. For avatar and for now we not store any images for user yet , once we ready then we enable to feature
 * 4. The current website do not have avatar function, please us static avatar for every user.
 * 5. For Service provider we only have create response and list of response and message
 * <p>
 * 6.详情界面：For view the request you can refer endpoint Service > MAIN > view request
 * For list of request please refer to Service > User > list of request
 * For list of response from request please refer to Service > User > list of response
 * For view the request you can refer endpoint Service > MAIN > view request
 * <p>
 * For my deal endpoint is not available since our part not finalize the flow of deal to be implement , mean while deal page is not available for temporary.
 * For service provider company profile is not available yet until future i will keep update it inside the same documentation postman.
 */
public class TaskFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.container)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.id_lv)
    ListView listview;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.action_bar)
    View actionBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitle;
    private MyResponseAdapter myResponseAdapter;
    //    private TaskFinishedAdapter finishedAdapter;
    //    private TaskUnlockedAdapter taskUnlockedadapter;
    private MyTaskAdapter myTaskAdapter;
    ActionBar bar;
    private ArrayList<String> list = new ArrayList<String>();
    private String authorization;

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_task;
    }

    @Override
    protected void prepareFragment() {
        bar = ActionBar.init(actionBar);
        bar.showNotify();
        bar.setTitle("My Tasks");
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myResponseAdapter = new MyResponseAdapter();
        recyclerView.setAdapter(myResponseAdapter);
        myResponseAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                //                startActivity(new Intent(getActivity(), TaskDetailActivity.class));
                startActivity(new Intent(getActivity(), MyRequestDetailActivity.class));
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_text, getData());
        tvTitle.setText(list.get(0));
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawers();
                tvTitle.setText(list.get(i));
                showLoadingView();
                changeUi(i);
            }
        });
    }

    private void changeUi(int i) {
        switch (i) {
            case 0:
                Call<ResponseListBean> responseListBeanCall = RetrofitUtil.getAPIService().getResponseList(1 + "", authorization);
                responseListBeanCall.enqueue(new CustomerCallBack<ResponseListBean>() {
                    @Override
                    public void onResponseResult(ResponseListBean response) {
                        L.v("LZF", response + "");
                        dismissLoadingView();
                        if (response.isStatus()) {
                        } else {
                            ToastUtils.showToast(response.getMsg());
                        }
                    }

                    @Override
                    public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                        dismissLoadingView();
                        if (errorMessage != null) {
                            ToastUtils.showToast(errorMessage.getMsg());
                        } else {
                            ToastUtils.showToast("isNetError：" + isNetError);
                        }
                    }
                });
                myResponseAdapter = new MyResponseAdapter();
                recyclerView.setAdapter(myResponseAdapter);
                myResponseAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Object object, View view, int position) {
                        //                        startActivity(new Intent(getActivity(), TaskDetailActivity.class));
                        startActivity(new Intent(getActivity(), MyRequestDetailActivity.class));
                    }
                });
                break;
            case 1:
                Call<RequestListBean> requestListBeanCall = RetrofitUtil.getAPIService().getRequestList(1 + "", authorization);
                requestListBeanCall.enqueue(new CustomerCallBack<RequestListBean>() {
                    @Override
                    public void onResponseResult(RequestListBean response) {
                        L.v("LZF", response + "");
                        dismissLoadingView();
                        if (response.isStatus()) {
                            myTaskAdapter = new MyTaskAdapter(response.getData());
                            recyclerView.setAdapter(myTaskAdapter);
                            myTaskAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Object object, View view, int position) {
                                    //                                    startActivity(new Intent(getActivity(), TaskDetailActivity.class));
                                    startActivity(new Intent(getActivity(), MyRequestDetailActivity.class));
                                }
                            });
                        } else {
                            ToastUtils.showToast(response.getMsg());
                            myTaskAdapter = new MyTaskAdapter(new ArrayList<RequestBean>());
                            recyclerView.setAdapter(myTaskAdapter);
                        }
                    }

                    @Override
                    public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                        dismissLoadingView();
                        if (errorMessage != null) {
                            ToastUtils.showToast(errorMessage.getMsg());
                            myTaskAdapter = new MyTaskAdapter(new ArrayList<RequestBean>());
                            recyclerView.setAdapter(myTaskAdapter);
                        } else {
                            ToastUtils.showToast("isNetError：" + isNetError);
                            myTaskAdapter = new MyTaskAdapter(new ArrayList<RequestBean>());
                            recyclerView.setAdapter(myTaskAdapter);
                        }
                    }
                });
                break;
            //            case 2:
            //                taskUnlockedadapter = new TaskUnlockedAdapter();
            //                recyclerView.setAdapter(taskUnlockedadapter);
            //                break;
            //            case 3:
            //                finishedAdapter = new TaskFinishedAdapter();
            //                recyclerView.setAdapter(finishedAdapter);
            //                break;
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        Call<TypeListBean> typeListBeanCall = RetrofitUtil.getAPIService().getTypeList();
        typeListBeanCall.enqueue(new CustomerCallBack<TypeListBean>() {
            @Override
            public void onResponseResult(TypeListBean response) {
                L.v("LZF", response + "");
            }

            @Override
            public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                L.v("LZF", errorMessage + "；" + isNetError);
            }
        });
    }


    @OnClick({R.id.iv_menu, R.id.iv_left, R.id.iv_notify, R.id.ll_coins, R.id.btn_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_left:
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_coins:
            case R.id.btn_right:
                ToastUtils.showToast("test coins");
                break;
            default:
                break;
        }

    }

    private ArrayList<String> getData() {
        //        list.add("All My Task");
        //        list.add("Pin");
        list.add("My Response");
        list.add("My Task");
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        UserBean userParams = new UserBean();
        userParams.setEmail("awesome@luqman.rocks");
        userParams.setPassword("test123");
        showLoadingView();
        Call<UserBean> gsonUserBeanCall = RetrofitUtil.getAPIService().loginTest(userParams);
        gsonUserBeanCall.enqueue(new CustomerCallBack<UserBean>() {
            @Override
            public void onResponseResult(UserBean response) {
                L.v("LZF", response + "");
                authorization = "Bearer " + response.getAccess_token();
                dismissLoadingView();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                L.v("LZF", errorMsg + "；" + isNetError);
            }
        });
        return rootView;
    }
}
