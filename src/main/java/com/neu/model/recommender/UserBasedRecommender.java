package com.neu.model.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.GenericUserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;

public class UserBasedRecommender {
    public static void main(String[] args) {
        try {
            // 加载用户数据文件
            DataModel dataModel = new FileDataModel(new File("user_data.csv"));

            // 使用余弦相似度计算用户相似度
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

//            similarity.userSimilarity();
//
//            // 输出用户相似度
//            for (long userID1 : similarityMatrix.keySet()) {
//                FastByIDMap<Double> userSimilarities = similarityMatrix.get(userID1);
//                for (long userID2 : userSimilarities.keySet()) {
//                    double similarityScore = userSimilarities.get(userID2);
//                    System.out.println("User " + userID1 + " and User " + userID2 + " have similarity score: " + similarityScore);
//                }
//            }
        } catch (IOException | TasteException e) {
            e.printStackTrace();
        }
    }

}
