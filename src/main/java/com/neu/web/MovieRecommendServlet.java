package com.neu.web;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.Session;
import com.neu.pojo.History;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.HistoryService;
import com.neu.service.MovieService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/movieRecommendServlet")
public class MovieRecommendServlet extends HttpServlet {
    MovieService movieService = new MovieService();

    HistoryService historyService = new HistoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        List<Movie> movies;
        if(session.getAttribute("updateRecommend") == null) {
            // 生成推荐池
            movies = movieService.recommendMovie(user, 100);
            session.setAttribute("moviePool", movies);
            session.setAttribute("updateRecommend", false);

            movies = getRandomMovie(movies, user);

        }else {
            boolean updateRecommend = (boolean) session.getAttribute("updateRecommend");
            if (!updateRecommend) {
                List<Movie> currentPool = (List<Movie>) session.getAttribute("moviePool");

                movies = getRandomMovie(currentPool, user);

            }else {
                // 生成新推荐池
                movies = movieService.recommendMovie(user, 100);
                session.setAttribute("moviePool", movies);
                session.setAttribute("updateRecommend", false);

                movies = getRandomMovie(movies, user);
            }

        }


        response.setContentType("application/json");

        String responseData = JSON.toJSONString(movies);

        response.getWriter().write(responseData);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public List<Movie> getRandomMovie(List<Movie> movies, User user) {

        List<Double> freqs = new ArrayList<>();
        int total = 0;
        for (int frequency: user.getFrequency()) {
            total += frequency;
            freqs.add((double) frequency);
        }

        for (int i = 0; i < freqs.size(); i++) {
            freqs.set(i, freqs.get(i) / total);
        }

        History wanted = new History();
        wanted.setUserId(user.getUserId());
        List<History> history = historyService.recent100(wanted);

        movies = removeRepeat(movies, history);

        int alreadyGet = 0;
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;
        int selectedMovie = -1;

        return null;

    }

    public List<Movie> removeRepeat(List<Movie> movies, List<History> histories) {
        List<Movie> newMovies = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            boolean flag = false;
            for (History history : histories) {
                if (history.getMovieId() == movie.getId()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                newMovies.add(movies.get(i));
            }

        }

        return newMovies;

    }

    public Movie selectMovieByType(String type, List<Movie> movies) {
        for (Movie movie : movies) {
            for (String item : movie.getType()) {
                if (item.equals(type)) {
                    return movie;
                }
            }
        }

        return null;

    }

}
