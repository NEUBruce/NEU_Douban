package com.neu.web;

import com.alibaba.fastjson.JSON;
import com.neu.pojo.User;
import com.neu.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            BufferedReader br = request.getReader();
            String jsonString = br.readLine();
            User user = JSON.parseObject(jsonString, User.class);

            //注册成功添加用户
            userService.insertUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
