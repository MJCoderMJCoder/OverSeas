package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

public class PhoneListBean extends BaseBean {

    private List<PhoneBean> data;

    public List<PhoneBean> getData() {
        return data;
    }

    public void setData(List<PhoneBean> data) {
        this.data = data;
    }
}
