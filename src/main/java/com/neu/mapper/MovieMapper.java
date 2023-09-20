package com.neu.mapper;

import com.neu.pojo.Movie;
import com.neu.pojo.User;

import java.util.List;

public interface MovieMapper {
    //查询电影列表
    List<Movie> selectAllMovie();

    //查询用户
    Movie selectMovieById(Movie movie);

    //创建用户基本信息
    int addMovie(Movie movie);

    //根据id删除用户信息
    int deleteMovieById(Movie movie);

    //编辑用户信息
    int modifyMovie(Movie movie);
}
