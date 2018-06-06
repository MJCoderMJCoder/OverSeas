package com.ltt.overseas.model;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class CreateResponseBodyBean {
    public CreateResponseBodyBean(String request_id){
        this.request_id=request_id;
    }
    private String request_id;
    public void setRequest_id(String request_id){this.request_id=request_id;}
    public String getRequest_id(){return request_id;}
}
