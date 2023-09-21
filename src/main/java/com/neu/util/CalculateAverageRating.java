package com.neu.util;

import com.neu.pojo.Movie;
import com.neu.service.MovieService;
import com.neu.service.RatingService;

import java.util.Collections;
import java.util.List;

public class CalculateAverageRating {
    private RatingService ratingService = new RatingService();
    private MovieService movieService = new MovieService();
    public void calculateAverageRating() {
        List<Movie> movies = movieService.selectAllMovie();

        for (Movie movie : movies) {
            System.out.println(movie);
            try {
                double avg = movieService.calculateAverageRating(movie);
                if (avg != 0.0) {
                    movie.setRate(avg);
                    movieService.modifyMovie(movie);
                }
            }catch (Exception e) {

            }


        }
    }

    public static void main(String[] args) {
        CalculateAverageRating service = new CalculateAverageRating();
        service.calculateAverageRating();
    }
}
