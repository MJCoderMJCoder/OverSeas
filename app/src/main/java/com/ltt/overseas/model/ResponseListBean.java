package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by MJCoder on 2018-05-09.
 */

public class ResponseListBean extends BaseBean {
    private List<ResponseBean> data;
    private String total;

    public List<ResponseBean> getData() {
        return data;
    }

    public void setData(List<ResponseBean> data) {
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
        return "ResponseListBean{" +
                "data=" + data +
                ", total='" + total + '\'' +
                "} " + super.toString();
    }
}
