package com.neu.service;

import com.neu.mapper.RatingMapper;
import com.neu.mapper.UserMapper;
import com.neu.pojo.Rating;
import com.neu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RatingService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    public void addRating(Rating rating) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RatingMapper mapper = sqlSession.getMapper(RatingMapper.class);
        mapper.addRating(rating);
        sqlSession.close();
    }

    public List<Rating> selectRatingByUserId(Rating rating) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RatingMapper mapper = sqlSession.getMapper(RatingMapper.class);
        List<Rating> ratings = mapper.selectByUserId(rating);
        sqlSession.close();

        return ratings;
    }
}
