package com.neu.mapper;

import com.neu.pojo.History;
import com.neu.pojo.User;

import java.util.List;

public interface HistoryMapper {

    int addHistory(History history);

    List<History> selectHistoryByUserId(History history);
}
