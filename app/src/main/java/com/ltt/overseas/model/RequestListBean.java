package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by MJCoder on 2018-05-09.
 */

public class RequestListBean extends BaseBean {
    private List<RequestBean> data;
    private String total;

    public List<RequestBean> getData() {
        return data;
    }

    public void setData(List<RequestBean> data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RequestListBean{" +
                "data=" + data +
                ", total='" + total + '\'' +
                "} " + super.toString();
    }
}
