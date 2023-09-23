package com.neu.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.pojo.Movie;
import com.neu.pojo.Rating;
import com.neu.pojo.User;
import com.neu.service.MovieService;
import com.neu.service.RatingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet("/click")
public class ClickServlet extends HttpServlet {
    private RatingService ratingService = new RatingService();
    private ObjectMapper objectMapper = new ObjectMapper(); // 初始化ObjectMapper

    private MovieService movieService = new MovieService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("application/json");

        // 从请求体中获取JSON数据
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // 解析JSON以获取信息
        JsonNode jsonNode = objectMapper.readTree(json.toString());
        String strMovieID = jsonNode.get("movieId").asText();
        long movieID = Long.parseLong(strMovieID);
        String strRating = jsonNode.get("rating").asText();
        Integer ratings = Integer.parseInt(strRating);

        User user = (User) session.getAttribute("user");
        // 创建Rating对象
        Rating rating = new Rating(user.getUserId(), movieID, ratings, new Date().getTime());



        // 调用addRating方法执行查询
        ratingService.addRating(rating);
        Movie movie = new Movie();
        movie.setId(movieID);
        movieService.updateMovieRate(movie);

    }
}