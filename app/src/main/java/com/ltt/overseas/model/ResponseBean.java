package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

/**
 * Created by MJCoder on 2018-05-09.
 */

public class ResponseBean extends BaseBean {
    private String quote_id;
    private String request_id;
    private String conversation_id;
    private String response_status;
    private String response_name;
    private String user;
    private String date_created;

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public String getResponse_name() {
        return response_name;
    }

    public void setResponse_name(String response_name) {
        this.response_name = response_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "quote_id='" + quote_id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", conversation_id='" + conversation_id + '\'' +
                ", response_status='" + response_status + '\'' +
                ", response_name='" + response_name + '\'' +
                ", user='" + user + '\'' +
                ", date_created='" + date_created + '\'' +
                "} " + super.toString();
    }
}
