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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {
    private RatingService ratingService = new RatingService();
    private ObjectMapper objectMapper = new ObjectMapper(); // 初始化ObjectMapper

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
        String strUserID = jsonNode.get("userID").asText();
        Integer userID = Integer.parseInt(strUserID);
        String strMovieID = jsonNode.get("movieID").asText();
        Integer movieID = Integer.parseInt(strMovieID);
        String strRating = jsonNode.get("rating").asText();
        Integer ratings = Integer.parseInt(strRating);
        String strTimestamp = jsonNode.get("rating").asText();
        Integer timestamp = Integer.parseInt(strTimestamp);

        // 创建Rating对象
        Rating rating = new Rating(userID, movieID, ratings, timestamp);


        // 调用addRating方法执行查询
        ratingService.addRating(rating);

        request.setAttribute("rating", rating);

        //页面重定向
        request.getRequestDispatcher("searchResult.jsp").forward(request, response);

    }
}