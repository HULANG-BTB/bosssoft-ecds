package com.bosssoft.ecds.entity.po;

import java.util.Date;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/24 19:14
 */
public class MailPo {

    int id;
    String name;
    String email;
    String operator;
    int operatorId;
    Date createDate;
    Date updateDate;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "MailPo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorId=" + operatorId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
