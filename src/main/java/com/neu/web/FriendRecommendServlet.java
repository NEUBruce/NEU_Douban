package com.neu.web;

import com.alibaba.fastjson.JSON;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/friendRecommendServlet")
public class FriendRecommendServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        List<User> users = userService.recommendUsers(user.getUserId(), 4);

        response.setContentType("application/json");

        String responseData = JSON.toJSONString(users);

        response.getWriter().write(responseData);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
