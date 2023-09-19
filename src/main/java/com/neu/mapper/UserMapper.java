package com.neu.mapper;

import com.neu.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> selectAllUser();
}
