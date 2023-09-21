package com.neu.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.pojo.User;
import com.neu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/friends")
public class FriendsServlet extends HttpServlet {

    private UserService userService = new UserService();
    private ObjectMapper objectMapper = new ObjectMapper(); // 初始化ObjectMapper

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //得到当前登录的user
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        List<User> friendsList = userService.selectAllFriends(currentUser);

        request.setAttribute("friendsList", friendsList);

        //页面重定向

    }

}


