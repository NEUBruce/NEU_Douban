package com.neu.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.MovieService;
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

@WebServlet("/addFriends")
public class AddFriendsServlet extends HttpServlet{
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

        // 获取用户输入的friendId
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // 解析JSON以获取friendId
        JsonNode jsonNode = objectMapper.readTree(json.toString());
        String strFriendId = jsonNode.get("friendId").asText();
        Integer friendId = Integer.parseInt(strFriendId);

        //得到当前登录的user
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if(currentUser!=null){
            Integer addResult=userService.addFriends(currentUser,friendId);
            request.setAttribute("addResult", addResult);
        }else{
            throw new RuntimeException("currentUser is null");
        }

        //页面重定向

    }

}


