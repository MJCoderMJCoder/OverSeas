package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by MJCoder on 2018-05-25.
 */

public class MyRequestDetailListBean extends BaseBean {
    //    private ExploreQuestionBean data;
    private List<ExploreQuestionListBean> data;

    public List<ExploreQuestionListBean> getData() {
        return data;
    }

    public void setData(List<ExploreQuestionListBean> data) {
        this.data = data;
    }
    //    public ExploreQuestionBean getData() {
    //        return data;
    //    }
    //
    //    public void setData(ExploreQuestionBean data) {
    //        this.data = data;
    //    }
}
