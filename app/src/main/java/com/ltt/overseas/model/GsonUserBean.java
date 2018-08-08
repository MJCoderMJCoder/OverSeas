package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

public class GsonUserBean extends BaseBean {

    private UserNewBean data;

    public UserNewBean getData() {
        return data;
    }

    public void setData(UserNewBean data) {
        this.data = data;
    }
}
