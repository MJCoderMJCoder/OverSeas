package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class CreateResponseBean extends BaseBean {
    private String conversation;
    public void setConversation(String conversation){this.conversation=conversation;}
    public String getConversation(){return conversation;}
}
