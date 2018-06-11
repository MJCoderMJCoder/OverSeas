package com.ltt.overseas.main.tab.fragment.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.main.tab.fragment.activity.ExploreActivity;
import com.ltt.overseas.model.SectionBean;
import com.ltt.overseas.model.TypeBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2018/1/18.
 */
public class SectionListAdapter extends RecyclerAdapter {

    private int normalBg;
    private int pressBg;
    private int selection;
    private List<Object> list = new ArrayList<>();
    private List<String> mCheckSectionList=new ArrayList<>();
    private Button btn_search;
    public SectionListAdapter(Context context, int normalBg, int pressBg) {
        initParams(normalBg, pressBg);
    }
    public String getSectionList(){
        if (mCheckSectionList==null&&mCheckSectionList.size()==0)
            return "";
        StringBuilder  sReturnIdList=new StringBuilder();
        for (String sectionid:mCheckSectionList){
            sReturnIdList.append(sectionid);
            sReturnIdList.append(",");
        }
        if (sReturnIdList.toString().endsWith(","))
        sReturnIdList.delete(sReturnIdList.length()-1,sReturnIdList.length());
        return sReturnIdList.toString();
    }

    private void initParams(int normalBg, int pressBg){
        this.normalBg = normalBg;
        this.pressBg = pressBg;
        this.selection = -1;
    }
    public void setBtn_search(Button btn_search){
        this.btn_search=btn_search;
    }


    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_item, parent, false));
    }

    @Override
    public int getContentItemCount() {
        return list.size();
    }

    public void addAll(Collection<? extends Object> collection) {
        if (collection == null) {
            return;
        }
        list.clear();
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public List<Object> getData() {
        return list;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SectionBean data = (SectionBean) getData().get(position);
        ((Holder) holder).tvName.setText(data.getSection_name());
        if(position == selection) {
            ((Holder) holder).tvName.setBackgroundResource(pressBg);
        } else {
            ((Holder) holder).tvName.setBackgroundResource(normalBg);
        }
        if (mCheckSectionList.contains(data.getSection_id()))
            ((Holder) holder).cb.setChecked(true);
        ((Holder) holder).cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (position>=list.size())
                    return;
                if (getItem(position) instanceof SectionBean){
                    String setcionid= ((SectionBean)getItem(position)).getSection_id();
                    if (b){
                        if(!mCheckSectionList.contains(setcionid))
                            mCheckSectionList.add(setcionid);
                    }else {
                        mCheckSectionList.remove(setcionid);
                    }
                }
                if (mCheckSectionList.size()==1 && btn_search!=null){
                    btn_search.setVisibility(View.VISIBLE);
                }else if(mCheckSectionList.size()==0 && btn_search!=null){
                    btn_search.setVisibility(View.GONE);
                }
            }
        });
    }

    public class Holder extends RecyclerHolder {
        public Holder(View itemView) {
            super(itemView);
        }
        TextView tvName = (TextView) itemView.findViewById(R.id.tv);
        CheckBox cb=itemView.findViewById(R.id.chb);
    }
}
