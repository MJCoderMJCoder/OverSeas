package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

public class GsonUserBean extends BaseBean {

    private UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
