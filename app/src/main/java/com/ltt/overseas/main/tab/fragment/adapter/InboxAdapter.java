package com.ltt.overseas.main.tab.fragment.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ltt.overseas.R;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.model.MessageListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class InboxAdapter extends BaseQuickAdapter<MessageListBean.DataBean, BaseViewHolder> {
    private List<MessageListBean.DataBean> mDataLists = new ArrayList<>();
    public InboxAdapter(@LayoutRes int layoutResId, @Nullable List<MessageListBean.DataBean> mMessageLists) {
        super(layoutResId, mMessageLists);
        this.mDataLists = mMessageLists;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean.DataBean dataBeen) {
        helper.setText(R.id.tv_nickname, dataBeen.getCurrent_user().getName());
        helper.setText(R.id.tv_request_category, dataBeen.getRequest_category());
        helper.setText(R.id.tv_conversation, dataBeen.getConversation_id());
        helper.setText(R.id.tv_date_created, dataBeen.getDate_created());
        helper.setText(R.id.tv_supplier, dataBeen.getOpposite_user().getName());
    }

}
