package com.jumpstartup.Freelancer;

import java.io.Serializable;

public class FreelancerBean  {
    private int uuid;
    private String phone_number;
    private String skills;
//    private byte[] resume_pdf;
    private String linkedin_link;

    public FreelancerBean() {}

    public FreelancerBean(int uuid, String phone_number, String skills, String linkedin_link) {
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.skills = skills;
//        this.resume_pdf = resume_pdf;
        this.linkedin_link = linkedin_link;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
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

//    public byte[] getResume_pdf() {
//        return resume_pdf;
//    }
//
//    public void setResume_pdf(byte[] resume_pdf) {
//        this.resume_pdf = resume_pdf;
//    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public void setLinkedin_link(String linkedin_link) {
        this.linkedin_link = linkedin_link;
    }
}
