package com.neu.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private Long userId;//用户id
    private String username;//用户名称

    private String password;//密码

    private String gender;//性别

    private String vocation;//职业

    private String zipCode;//邮编

    private Integer age;//年龄

    private List<User> friends;//关注好友

    private List<Integer> frequency= Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);

    private String frequencyInfo;

    public User(Long userId, String username, String password, String gender, String vocation, String zipCode, Integer age, List<User> friends, List<Integer> frequency, String frequencyInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.vocation = vocation;
        this.zipCode = zipCode;
        this.age = age;
        this.friends = friends;
        this.frequency = frequency;
        this.frequencyInfo = frequencyInfo;
    }

    public String getFrequencyInfo() {
        return frequencyInfo;
    }

    public void setFrequencyInfo(String frequencyInfo) {
        String[] frequencies = frequencyInfo.split(",");
        this.frequency = new ArrayList<Integer>();

        for (String f : frequencies) {
            frequency.add(Integer.parseInt(f));
        }

        this.frequencyInfo = frequencyInfo;
    }

    public User() {
    }

    public List<Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(List<Integer> frequency) {
        this.frequency = frequency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
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
                ", friends=" + friends +
                '}';
    }
}
