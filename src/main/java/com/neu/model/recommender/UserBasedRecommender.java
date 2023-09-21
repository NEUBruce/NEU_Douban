package com.neu.model.recommender;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.knn.KnnItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.knn.NonNegativeQuadraticOptimizer;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.Collection;
import java.util.List;

public class UserBasedRecommender implements Recommender {
    public List<RecommendedItem> getUserBasedRecommender(DataModel dataModel) throws TasteException {
        // 欧几里得计算物品相似度
        ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
        KnnItemBasedRecommender recommender = new KnnItemBasedRecommender(dataModel, itemSimilarity, new NonNegativeQuadraticOptimizer(), 10);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }

    }

    public List<RecommendedItem> recommend(long l, int i) throws TasteException {
        return null;
    }

    public List<RecommendedItem> recommend(long l, int i, IDRescorer idRescorer) throws TasteException {
        return null;
    }

    public float estimatePreference(long l, long l1) throws TasteException {
        return 0;
    }

    public void setPreference(long l, long l1, float v) throws TasteException {

    }

    public void removePreference(long l, long l1) throws TasteException {

    }

    public DataModel getDataModel() {
        return null;
    }

    public void refresh(Collection<Refreshable> collection) {

    }
}
