package com.neu.service;

import com.neu.mapper.MovieMapper;
import com.neu.mapper.UserMapper;
import com.neu.model.recommender.MixedRecommender;
import com.neu.model.recommender.UserCFRecommender;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    private UserCFRecommender userCFRecommender = new UserCFRecommender();
    private RatingService ratingService = new RatingService();

    //获取所有movie
    public List<Movie> selectAllMovie() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movieList = mapper.selectAllMovie();
        sqlSession.commit();
        sqlSession.close();
        return movieList;
    }

    //通过movieId搜索movie
    public Movie selectMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        Movie selectMovie = mapper.selectMovieById(movie);
        sqlSession.commit();
        sqlSession.close();
        return selectMovie;
    }

    //增加movie
    public int addMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.addMovie(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    //修改movie
    public int modifyMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.modifyMovie(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    //删除movie
    public int deleteMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.deleteMovieById(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    //搜索功能
    public List<Movie> searchMovie(String searchMessage){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movieList = mapper.searchMovie(searchMessage);
        sqlSession.commit();
        sqlSession.close();
        return movieList;
    }

    //计算movie的平均得分
    public double calculateAverageRating(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        double avg = mapper.calculateAverageRating(movie);
        sqlSession.close();
        return avg;
    }

    //得到排名前100的movie
    public List<Movie> top100Movies() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movies = mapper.top100Movie();
        sqlSession.close();
        return movies;
    }

    //获取推荐movie的列表
    public List<Movie> recommendMovie(User user, int size) {
        //检测是否冷启动
        MixedRecommender recommender = new MixedRecommender(user);

        return recommender.recommend();
    }

    public void updateMovieRate(Movie movie) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        movie = mapper.selectMovieById(movie);
        double rate = mapper.calculateAverageRating(movie);
        movie.setRate(rate);
        mapper.modifyMovie(movie);
        sqlSession.close();
    }
}
