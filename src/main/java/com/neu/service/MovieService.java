package com.neu.service;

import com.neu.mapper.MovieMapper;
import com.neu.mapper.UserMapper;
import com.neu.model.recommender.ModelCFRecommender;
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

    public List<Movie> selectAllMovie() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movieList = mapper.selectAllMovie();
        sqlSession.commit();
        sqlSession.close();
        return movieList;
    }

    public Movie selectMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        Movie selectMovie = mapper.selectMovieById(movie);
        sqlSession.commit();
        sqlSession.close();
        return selectMovie;
    }

    public int addMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.addMovie(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public int modifyMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.modifyMovie(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public int deleteMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        int result = mapper.deleteMovieById(movie);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public List<Movie> searchMovie(String searchMessage){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movieList = mapper.searchMovie(searchMessage);
        sqlSession.commit();
        sqlSession.close();
        return movieList;
    }

    public double calculateAverageRating(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        double avg = mapper.calculateAverageRating(movie);
        sqlSession.close();
        return avg;
    }

    public List<Movie> top100Movies() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movies = mapper.top100Movie();
        sqlSession.close();
        return movies;
    }

    public List<Movie> recommendMovie(User user, int size) {
        //检测是否冷启动


        List<RecommendedItem> movieIds = userCFRecommender.getUserCFRecommender(user.getUserId(), size);
        List<Movie> movies = new ArrayList<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);

        for (RecommendedItem item : movieIds) {
            Movie movie = new Movie();
            movie.setId(item.getItemID());
            movie = mapper.selectMovieById(movie);
            movies.add(movie);
        }
        sqlSession.close();

        return movies;
    }
}
