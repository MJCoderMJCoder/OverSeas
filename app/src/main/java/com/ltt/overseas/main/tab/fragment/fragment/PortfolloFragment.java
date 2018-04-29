package com.ltt.overseas.main.tab.fragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseFragment;
import com.ltt.overseas.main.tab.fragment.adapter.PortFolloAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.TaskAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/24.
 */
public class PortfolloFragment extends BaseFragment {
    @BindView(R.id.container)
    SwipeRecyclerView recyclerView;
    private PortFolloAdapter adapter;
    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_portfollo;
    }

    @Override
    protected void prepareFragment() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        adapter = new PortFolloAdapter();
        recyclerView.setAdapter(adapter);
    }

}
