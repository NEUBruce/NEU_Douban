package com.neu.mapper;

import com.neu.pojo.Rating;

import java.util.List;

public interface RatingMapper {
    //增加打分记录
    public void addRating(Rating rating);

    //通过用户id获取打分记录
    public List<Rating> selectByUserId(Rating rating);

}
