package com.ltt.overseas.model;

public class CompanyBean {

    /**
     * company_name : TEST
     * registered_number : test
     * contact_number :
     * address :
     * country_id : 148
     * states_id : 1
     * description :
     */

    private String company_name;
    private String registered_number;
    private String contact_number;
    private String address;
    private int country_id;
    private int states_id;
    private String description;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getRegistered_number() {
        return registered_number;
    }

    public void setRegistered_number(String registered_number) {
        this.registered_number = registered_number;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getStates_id() {
        return states_id;
    }

    public void setStates_id(int states_id) {
        this.states_id = states_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
