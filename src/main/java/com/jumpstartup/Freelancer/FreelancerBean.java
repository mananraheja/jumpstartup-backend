package com.jumpstartup.Freelancer;

import java.io.Serializable;

public class FreelancerBean  {

    private String uuid;
    private String username;
    private String email;

    // FREELANCER
    private String phone_number;
    private String skills;
    private String linkedin_link;

    // EDUCATION
    private String institution;

    private String degree;
    private String major;
    private int year_of_completion;

    // WORK EXPERIENCE
    private String work_experience;



    public FreelancerBean() {}

    public FreelancerBean(String UUID,String username, String email, String phone_number, String skills, String linkedin_link, String institution, String degree, String major, int year_of_completion, String work_experience) {
        this.uuid = UUID;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.skills = skills;
        this.linkedin_link = linkedin_link;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public void setLinkedin_link(String linkedin_link) {
        this.linkedin_link = linkedin_link;
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
