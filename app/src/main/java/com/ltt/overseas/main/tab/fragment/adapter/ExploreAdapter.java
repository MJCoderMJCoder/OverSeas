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
    public void add( ExploreQuestionListBean collection) {
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
        final ExploreQuestionListBean data = (ExploreQuestionListBean)(list.get(position));
        ((ExploreAdapter.Holder) holder).tvName.setText(data.getQuestion_title());
    }

    public class Holder extends RecyclerHolder {

        @BindView(R.id.rl_1)
        RelativeLayout rl1;
        @BindView(R.id.rl_2)
        LinearLayout rl2;
        @BindView(R.id.rl_3)
        LinearLayout rl3;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
        TextView tvName = (TextView) itemView.findViewById(R.id.explore_item_title);
    }
}
