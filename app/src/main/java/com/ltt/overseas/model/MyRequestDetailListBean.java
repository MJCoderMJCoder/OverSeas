package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by MJCoder on 2018-05-25.
 */

public class MyRequestDetailListBean extends BaseBean {
    //    private ExploreQuestionBean data;
    //
    //    public ExploreQuestionBean getData() {
    //        return data;
    //    }
    //
    //    public void setData(ExploreQuestionBean data) {
    //        this.data = data;
    //    }

    private List<MyRequestDetail> data;

    public List<MyRequestDetail> getData() {
        return data;
    }

    public void setData(List<MyRequestDetail> data) {
        this.data = data;
    }
}
