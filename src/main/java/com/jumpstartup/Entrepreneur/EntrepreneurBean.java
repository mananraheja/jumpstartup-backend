package com.jumpstartup.Entrepreneur;

public class EntrepreneurBean {

    private String uuid;
    private String phone_number;
    private String domain;

    // EDUCATION
    private String institution;
    private String degree;
    private String major;
    private String year_of_completion;

    //Work Ex
    private String work_experience;



    public EntrepreneurBean() {}

    public EntrepreneurBean(String uuid, String phone_number, String domain, String institution, String degree, String major, String year_of_completion, String work_experience) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.domain = domain;
        this.institution = institution;
        this.degree = degree;
        this.major = major;
        this.year_of_completion = year_of_completion;
        this.work_experience = work_experience;
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

    public String getYear_of_completion() {
        return year_of_completion;
    }

    public void setYear_of_completion(String year_of_completion) {
        this.year_of_completion = year_of_completion;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }
}


