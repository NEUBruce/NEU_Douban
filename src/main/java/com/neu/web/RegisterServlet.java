package com.neu.web;

import com.neu.pojo.User;
import com.neu.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 从请求的输入流中读取JSON数据并将其解析为User对象
            User user = objectMapper.readValue(request.getInputStream(), User.class);

            //注册成功添加用户
           userService.insertUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
