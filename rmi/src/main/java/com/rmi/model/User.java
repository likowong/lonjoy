package com.rmi.model;

import java.io.Serializable;

/**
 * @author luke
 * @date 2021/1/6 0006 13:53
 * @desc
 **/
public class User implements Serializable {
    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
