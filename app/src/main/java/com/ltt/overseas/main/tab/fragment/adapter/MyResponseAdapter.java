package com.ltt.overseas.main.tab.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.model.ResponseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class MyResponseAdapter extends RecyclerAdapter {
    private List<ResponseBean> dataList;

    public MyResponseAdapter(List<ResponseBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_response, parent, false));
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
        ((Holder) holder).tv_hint.setText(dataList.get(position).getResponse_name());
        ((Holder) holder).dateTime.setText(dataList.get(position).getDate_created());
        ((Holder) holder).name.setText(dataList.get(position).getUser());
    }

    public class Holder extends RecyclerHolder {
        private TextView tv_hint;
        private TextView dateTime;
        private TextView name;

        public Holder(View itemView) {
            super(itemView);
            tv_hint = (TextView) itemView.findViewById(R.id.tv_hint);
            dateTime = (TextView) itemView.findViewById(R.id.dateTime);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public void add(int position, ResponseBean data) {
        if (position < 0) {
            dataList.add(data);
        } else {
            dataList.add(position, data);
        }
        notifyDataSetChanged();
    }

    public void updateAll(List<ResponseBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

}
