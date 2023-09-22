package com.neu.web;

import com.alibaba.fastjson.JSON;
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
import java.util.Random;

@WebServlet("/movieRecommendServlet")
public class MovieRecommendServlet extends HttpServlet {
    MovieService movieService = new MovieService();

    HistoryService historyService = new HistoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        List<Movie> movies;

//        if(session.getAttribute("updateRecommend") == null) {
//            // 生成推荐池
//            movies = movieService.recommendMovie(user, 100);
//            System.out.println(movies);
//            session.setAttribute("moviePool", movies);
//            session.setAttribute("updateRecommend", false);
//
//            movies = getRandomMovie(movies, user);
//
//        }else {
//            boolean updateRecommend = (boolean) session.getAttribute("updateRecommend");
//            if (!updateRecommend) {
//                List<Movie> currentPool = (List<Movie>) session.getAttribute("moviePool");
//
//                movies = getRandomMovie(currentPool, user);
//
//            }else {
//                // 生成新推荐池
//                movies = movieService.recommendMovie(user, 100);
//                session.setAttribute("moviePool", movies);
//                session.setAttribute("updateRecommend", false);
//
//                movies = getRandomMovie(movies, user);
//            }
//
//        }

        movies = movieService.recommendMovie(user, 100);

        System.out.println(movies);

        // 创建一个Random对象
        Random random = new Random();

        // 存储随机选择的5个元素的列表
        ArrayList<Movie> selectedElements = new ArrayList<>();

        // 从ArrayList中随机选择5个元素
        int numElementsToSelect = 5;
        while (selectedElements.size() < numElementsToSelect) {
            // 生成一个随机索引
            int randomIndex = random.nextInt(movies.size());

            // 从ArrayList中移除选定的元素并添加到selectedElements列表中
            selectedElements.add(movies.remove(randomIndex));
        }

        movies = selectedElements;


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

        List<Movie> recommendMovies = new ArrayList<>();
        while (alreadyGet <= 5) {
            // 数量太少直接更新池子
            if (movies.size() <= 5) {
                movies = movieService.recommendMovie(user, 100);
            }
            String type = randomType(freqs);
            Movie movie = selectMovieByType(type, movies);
            if (movie == null) {
                continue;
            } else {
                recommendMovies.add(movie);
            }

        }

        return recommendMovies;
    }

    public String randomType(List<Double> freqs) {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;
        int selectedMovie = -1;

        for (int i = 0; i < freqs.size(); i++) {
            cumulativeProbability += freqs.get(i);

            if (randomValue <= cumulativeProbability) {
                selectedMovie = i;
                break;
            }
        }

        return "" + selectedMovie;
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
