package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

public class TypeListBean extends BaseBean {

    private List<TypeBean> data;

    public List<TypeBean> getData() {
        return data;
    }

    public void setData(List<TypeBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TypeListBean{" +
                "data=" + data +
                "} " + super.toString();
    }
}
