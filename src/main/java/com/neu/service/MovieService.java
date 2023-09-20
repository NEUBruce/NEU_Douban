package com.neu.service;

import com.neu.mapper.MovieMapper;
import com.neu.mapper.UserMapper;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MovieService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Movie> selectAllMovie() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movieList = mapper.selectAllMovie();
        sqlSession.close();
        return movieList;
    }

    public Movie selectMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        return mapper.selectMovieById(movie);
    }

    public int addMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        return mapper.addMovie(movie);
    }

    public int modifyMovie(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        return mapper.modifyMovie(movie);
    }

    public int deleteMovieById(Movie movie){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        return mapper.deleteMovieById(movie);
    }
}
