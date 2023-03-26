package com.jumpstartup.Investor;

public class InvestorBean {

    private String uuid;
    private String phone_number;
    private String domain;
    private float funding_available;
    private String brands_built;

    // EDUCATION
    private String institution;
    private String degree;
    private String major;
    private int year_of_completion;

    // WORK EXPERIENCE
    private String work_experience;


    public InvestorBean() {}

    public InvestorBean(String uuid, String phone_number, String domain, float funding_available, String brands_built) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.domain = domain;
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

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear_of_completion() {
        return year_of_completion;
    }

    public void setYear_of_completion(int year_of_completion) {
        this.year_of_completion = year_of_completion;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }
}


