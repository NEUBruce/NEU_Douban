package com.neu.service;

import com.neu.mapper.UserMapper;
import com.neu.pojo.User;
import com.neu.util.DataModelUtil;
import com.neu.util.SqlSessionFactoryUtils;
import com.neu.util.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<User> selectAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.selectAllUser();
        sqlSession.close();
        return userList;
    }

    public List<User> selectUserById(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.selectUserById(user);
        sqlSession.close();
        return userList;
    }

    public User selectUserByName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User selectedUser = mapper.selectUserByName(user);
        sqlSession.close();

        return selectedUser;
    }

    public int insertUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.insertUser(user);
    }

    public int modifyUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.modifyUser(user);
    }

    //删除用户
    public int deleteUserByName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.deleteUserByName(user);
    }

    //查找所有关注
    public List<User> selectAllFriends(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.selectAllFriends(user);
    }

    //增加关注
    public int addFriends(User user,Integer friendId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.addFriends(user,friendId);
    }

    //删除关注
    public int deleteFriends(User user,Integer friendId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.deleteFriends(user,friendId);
    }
    public List<User> recommendUsers(Long userId, int size) {
        try {
            DataModel model = DataModelUtil.getPreferenceDataModel();//构造数据模型
            UserSimilarity similarity = new UncenteredCosineSimilarity(model);//用PearsonCorrelation 算法计算用户相似度
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(size, similarity, model);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
            long[] neighbors = neighborhood.getUserNeighborhood(userId);

            List<User> users = new ArrayList<>();
            for (long id : neighbors) {
                User user = new User();
                user.setUserId(id);
                users.add(selectUserById(user).get(0));
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
