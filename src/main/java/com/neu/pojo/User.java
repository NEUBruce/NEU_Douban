package com.neu.pojo;

public class User {
    private String userId;
    private String username;

    private String password;

    private String gender;

    private String vocation;

    private String zipCode;

    private Integer age;

    public User(String userId, String username, String password, String gender, String vocation, String zipCode, Integer age) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.vocation = vocation;
        this.zipCode = zipCode;
        this.age = age;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", vocation='" + vocation + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", age=" + age +
                '}';
    }
}
