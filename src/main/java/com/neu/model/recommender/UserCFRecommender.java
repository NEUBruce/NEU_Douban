package com.neu.model.recommender;

import com.neu.pojo.User;
import com.neu.util.DataModelUtil;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import java.util.*;

public class UserCFRecommender {
	public List<RecommendedItem> getUserCFRecommender(Long id, int size) {
		// step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
		List<RecommendedItem> recommendations = null;
		try {
			DataModel model = DataModelUtil.getPreferenceDataModel();//构造数据模型
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//用PearsonCorrelation 算法计算用户相似度
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
			Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, similarity));//采用 CachingRecommender 为 RecommendationItem 进行缓存
			recommendations = recommender.recommend(id, size);//得到推荐的结果，size是推荐结果的数目
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recommendations;
	}

	public static void main(String args[]) throws Exception {
		UserCFRecommender recommender = new UserCFRecommender();

		List<RecommendedItem> list = recommender.getUserCFRecommender(1L, 2);

		for (RecommendedItem item : list) {
			System.out.printf("(%s,%f)", item.getItemID(), item.getValue());
		}


	}
}