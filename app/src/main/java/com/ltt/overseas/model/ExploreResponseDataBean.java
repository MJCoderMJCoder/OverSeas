package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class ExploreResponseDataBean extends BaseBean {

    private List<ExploreQuestionBean> data;
    public List<ExploreQuestionBean> getData(){return data;}
    public void setData(List<ExploreQuestionBean> data){this.data=data;}
}
