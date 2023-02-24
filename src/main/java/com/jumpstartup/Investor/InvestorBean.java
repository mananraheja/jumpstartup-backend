package com.jumpstartup.Investor;

public class InvestorBean {

    private String uuid;
    private String phone_number;
    private String domain;
    private float funding_available;
    private String brands_built;

    public InvestorBean() {}

    public InvestorBean(String uuid, String phone_number, float funding_available, String brands_built) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.funding_available = funding_available;
        this.brands_built = brands_built;
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

    public float getFunding_available() {
        return funding_available;
    }

    public void setFunding_available(float funding_available) {
        this.funding_available = funding_available;
    }

    public String getBrands_built() {
        return brands_built;
    }

    public void setBrands_built(String brands_built) {
        this.brands_built = brands_built;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}


