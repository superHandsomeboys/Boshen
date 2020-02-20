package com.gpnu.boshen1.Bean;

import org.apache.ibatis.annotations.Result;

import java.sql.Blob;

public class CompanyMember {
    private Integer member_id;
    private String member_name;
    private String position;
    private String introduce;
    private String avatar;

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "CompanyMember{" +
                "member_id=" + member_id +
                ", member_name='" + member_name + '\'' +
                ", position='" + position + '\'' +
                ", introduce='" + introduce + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
