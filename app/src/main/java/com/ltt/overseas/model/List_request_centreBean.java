package com.ltt.overseas.model;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class List_request_centreBean {
    private String request_id;
    private String date_created	;
    private String  request_title;
    private String total_quote;
    private String requester_name;
    private String show_button_response;
    public void setRequest_id(String request_id){this.request_id=request_id;}
    public String getRequest_id(){return request_id;}
    public void setDate_created(String date_created){this.date_created=date_created;}
    public String getDate_created(){return date_created;}
    public void setRequest_title(String request_title){this.request_title=request_title;}
    public String getRequest_title(){return request_title;}
    public void setTotal_quote(String total_quote){this.total_quote=total_quote;}
    public String getTotal_quote(){return total_quote;}
    public void setRequester_name(String requester_name){this.requester_name=requester_name;}
    public String getRequester_name(){return requester_name;}
    public void setShow_button_response(String show_button_response){this.show_button_response=show_button_response;}
    public String getShow_button_response(){return show_button_response;}
}
