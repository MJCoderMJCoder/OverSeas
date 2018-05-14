package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class ExploreQuestionBean{
    private String request	;
    private String user;
    private List< ExploreQuestionListBean> questions;
    public String getRequest(){return this.request;}
    public void setRequest(String request){this.request=request;}
    public String getUser(){return user;}
    public void setUser(String user){this.user=user;}
    public List<ExploreQuestionListBean> getQuestions(){return questions;}
    public void setQuestions(List<ExploreQuestionListBean> questions){this.questions=questions;}
}
