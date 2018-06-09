package com.ltt.overseas.main.tab.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.model.ExploreQuestionBean;
import com.ltt.overseas.model.ExploreQuestionListBean;
import com.ltt.overseas.model.ListQuestionBean;
import com.ltt.overseas.model.List_request_centreBean;
import com.ltt.overseas.model.TypeBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/21.
 */
public class ExploreAdapter extends RecyclerAdapter {
    private List<Object> list = new ArrayList<>();
    int selected=-1;
    public void setSelected(int selected){
        this.selected=selected;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        if(viewType!=3)
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore, parent, false));
        else
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_another, parent, false));

    }
    public void addAll(Collection<? extends Object> collection) {
        if (collection == null) {
            return;
        }
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public void add( List_request_centreBean collection) {
        list.add(collection);
        notifyDataSetChanged();
    }
    @Override
    public int getContentItemCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final List_request_centreBean data = (List_request_centreBean)(list.get(position));
        ((ExploreAdapter.Holder) holder).tvName.setText(data.getRequest_title());
        ((Holder) holder).tvUsername.setText(data.getRequester_name());
        ((Holder) holder).tvTime.setText(data.getDate_created());
        if (selected == position){
            ((Holder) holder).rl2.setBackgroundResource(R.mipmap.bg_item_explore_black);
        }
        else
            ((Holder) holder).rl2.setBackgroundResource(R.mipmap.bg_item_explore);
    }

    public class Holder extends RecyclerHolder {

//        @BindView(R.id.rl_1)
//        RelativeLayout rl1;
        @BindView(R.id.rl_2)
        LinearLayout rl2;
//        @BindView(R.id.rl_3)
//        LinearLayout rl3;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        TextView tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
        TextView tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        TextView tvName = (TextView) itemView.findViewById(R.id.explore_item_title);

    }
}
