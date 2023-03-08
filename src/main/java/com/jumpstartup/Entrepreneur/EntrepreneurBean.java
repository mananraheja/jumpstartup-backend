package com.jumpstartup.Entrepreneur;

public class EntrepreneurBean {

    private String uuid;
    private String phone_number;
    private String domain;


    public EntrepreneurBean() {}

    public EntrepreneurBean(String uuid, String phone_number, String domain) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.domain = domain;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}


