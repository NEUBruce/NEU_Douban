package com.neu.mapper;

import com.neu.pojo.History;
import com.neu.pojo.User;

import java.util.List;

public interface HistoryMapper {

    //增加历史记录
    int addHistory(History history);

    //通过用户id获取历史推荐记录
    List<History> selectHistoryByUserId(History history);

    //获取最近的100个历史推荐记录
    List<History> recent100History(History history);
}
