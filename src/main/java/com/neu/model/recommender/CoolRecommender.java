package com.neu.model.recommender;

import com.neu.pojo.Movie;
import com.neu.pojo.Rating;
import com.neu.pojo.User;
import com.neu.service.MovieService;
import com.neu.service.RatingService;
import com.neu.util.DataModelUtil;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CoolRecommender{

    private User currentUser;
    private RatingService ratingService = new RatingService();
    private MovieService movieService = new MovieService();

    public CoolRecommender(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Movie> recommend() {

        if(checkCoolRecommend()) {

        } else {

        }

        return null;
    }

    private boolean checkCoolRecommend() {
        Rating rating = new Rating();
        rating.setUserId(currentUser.getUserId());
        List<Rating> historyRatings = ratingService.selectRatingByUserId(rating);
        boolean allOnes = true;
        List<Integer> frequencies = currentUser.getFrequency();
        for (int frequency : frequencies) {
            if (frequency != 1) {
                allOnes = false;
            }
        }

        return allOnes && historyRatings.size() == 0;
    }
}
