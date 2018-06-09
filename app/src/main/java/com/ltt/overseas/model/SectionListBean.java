package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

public class SectionListBean extends BaseBean {

    private List<TypeSectionBean> data;

    public List<TypeSectionBean> getData() {
        return data;
    }

    public void setData(List<TypeSectionBean> data) {
        this.data = data;
    }
}
