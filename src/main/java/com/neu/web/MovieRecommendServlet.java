package com.neu.web;

import com.alibaba.fastjson.JSON;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.MovieService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/movieRecommendServlet")
public class MovieRecommendServlet extends HttpServlet {
    MovieService movieService = new MovieService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        List<Movie> movies = movieService.recommendMovie(user, 100);

        response.setContentType("application/json");

        String responseData = JSON.toJSONString(movies);

        response.getWriter().write(responseData);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
