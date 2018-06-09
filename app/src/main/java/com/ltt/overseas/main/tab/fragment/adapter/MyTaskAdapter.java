package com.ltt.overseas.main.tab.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.model.RequestBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class MyTaskAdapter extends RecyclerAdapter {
    private List<RequestBean> dataList;

    public MyTaskAdapter(List<RequestBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_task, parent, false));
    }

    @Override
    public int getContentItemCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder) holder).dateTime.setText(dataList.get(position).getDate_created());
        ((Holder) holder).tv_hint.setText(dataList.get(position).getRequest_name());
    }

    public class Holder extends RecyclerHolder {

        private TextView dateTime;
        private TextView tv_hint;

        public Holder(View itemView) {
            super(itemView);
            dateTime = (TextView) itemView.findViewById(R.id.dateTime);
            tv_hint = (TextView) itemView.findViewById(R.id.tv_hint);
        }
    }

    public void add(int position, RequestBean data) {
        if (position < 0) {
            dataList.add(data);
        } else {
            dataList.add(position, data);
        }
        notifyDataSetChanged();
    }

    public void updateAll(List<RequestBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

}
