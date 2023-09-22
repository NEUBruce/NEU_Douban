package com.neu.web;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.Session;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.HistoryService;
import com.neu.service.MovieService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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

        }else {
            movies = movieService.recommendMovie(user, 100);
        }


        response.setContentType("application/json");

        String responseData = JSON.toJSONString(movies);

        response.getWriter().write(responseData);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public List<Movie> getRandomMovie(List<Movie> movies) {
        return null;
    }

}
