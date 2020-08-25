package com.bosssoft.ecds.entity.dto;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/25 9:16
 */
public class MailDto {

    int id;
    String name;
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
