package com.neu.service;

import com.neu.mapper.HistoryMapper;
import com.neu.mapper.MovieMapper;
import com.neu.pojo.History;
import com.neu.pojo.Movie;
import com.neu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;

public class HistoryService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //增添历史记录
    public int addHistory(History history) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        history.setTimestamp(new Date().getTime());
        HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
        int result = mapper.addHistory(history);
        sqlSession.commit();
        sqlSession.close();
        return result;

    }

    //查询最近的100个历史记录
    public List<History> recent100(History history) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
        List<History> histories = mapper.recent100History(history);
        sqlSession.close();
        return histories;
    }
}
