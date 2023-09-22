package com.neu.model.recommender;

import com.neu.pojo.Movie;
import com.neu.pojo.Rating;
import com.neu.pojo.User;
import com.neu.service.MovieService;
import com.neu.service.RatingService;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.ArrayList;
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
            System.out.println("Cool start!");
            // 冷启动就选取评分最高前100
            return movieService.top100Movies();
        } else {
            System.out.println("hot start");
            List<RecommendedItem> userRecommend = userCFRecommender.getUserCFRecommender(currentUser.getUserId(), 50);
            List<RecommendedItem> itemRecommend = itemCFRecommender.getItemCFRecommender(currentUser.getUserId(), 50);

            List<Movie> movies = new ArrayList<>();

            for (RecommendedItem item : itemRecommend) {
                Long id = item.getItemID();
                Movie movie = new Movie();
                movie.setId(id);
                movie = movieService.selectMovieById(movie);
                movies.add(movie);
            }

            for (RecommendedItem item : userRecommend) {
                Long id = item.getItemID();
                Movie movie = new Movie();
                movie.setId(id);
                movie = movieService.selectMovieById(movie);
                for(int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getId() != movie.getId()) {
                        movies.add(movie);
                    }
                }

            }

            return movies;
        }
    }

    private boolean checkCoolRecommend() {
        Rating rating = new Rating();
        rating.setUserId(currentUser.getUserId());
        System.out.println(rating.getUserId());
        List<Rating> historyRatings = ratingService.selectRatingByUserId(rating);
        System.out.println("History rating: " + historyRatings);
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
