package com.ltt.overseas.model;

import java.util.List;

/**
 * Created by MJCoder on 2018-06-07.
 */

public class MyRequestDetail {
    private String question_title;
    private List<String> question_answer;

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public List<String> getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(List<String> question_answer) {
        this.question_answer = question_answer;
    }
}
