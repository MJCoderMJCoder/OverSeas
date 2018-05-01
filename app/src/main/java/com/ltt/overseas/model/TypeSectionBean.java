package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

import java.util.List;

public class TypeSectionBean extends BaseBean {

    private TypeBean type;
    private List<SectionBean> scetion_list;

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public List<SectionBean> getScetion_list() {
        return scetion_list;
    }

    public void setScetion_list(List<SectionBean> scetion_list) {
        this.scetion_list = scetion_list;
    }

}
