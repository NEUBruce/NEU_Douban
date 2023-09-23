package com.neu.mapper;

import com.neu.pojo.Movie;
import com.neu.pojo.User;

import java.util.List;

public interface MovieMapper {
    //查询电影列表
    List<Movie> selectAllMovie();

    //通过id查询电影
    Movie selectMovieById(Movie movie);

    //增加电影
    int addMovie(Movie movie);

    //根据id删除电影
    int deleteMovieById(Movie movie);

    //编辑电影信息
    int modifyMovie(Movie movie);

    //搜索电影
    List<Movie> searchMovie(String searchMessage);

    //计算电影平均得分
    double calculateAverageRating(Movie movie);

    //获取得分前100的电影列表集合
    List<Movie> top100Movie();
}
