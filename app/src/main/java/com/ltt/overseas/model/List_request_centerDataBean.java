package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class List_request_centerDataBean extends BaseBean {
    private List<List_request_centreBean> data;

    public List<List_request_centreBean> getData() {
        return data;
    }

    public void setData(List<List_request_centreBean> data) {
        this.data = data;
    }
}
