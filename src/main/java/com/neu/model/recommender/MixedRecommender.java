package com.neu.model.recommender;

import com.neu.pojo.Movie;
import com.neu.pojo.Rating;
import com.neu.pojo.User;
import com.neu.service.MovieService;
import com.neu.service.RatingService;

import java.util.List;

public class MixedRecommender {

    private User currentUser;
    private RatingService ratingService = new RatingService();
    private MovieService movieService = new MovieService();
    private UserCFRecommender userCFRecommender = new UserCFRecommender();
    private ItemCFRecommender itemCFRecommender = new ItemCFRecommender();

    public MixedRecommender(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Movie> recommend() {

        if(checkCoolRecommend()) {
            // 冷启动就选取评分最高前100
            return movieService.top100Movies();
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
