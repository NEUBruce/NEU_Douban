package com.neu.mapper;

import com.neu.pojo.Rating;

import java.util.List;

public interface RatingMapper {
    public void addRating(Rating rating);

    public List<Rating> selectByUserId(Rating rating);

}
