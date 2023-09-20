package com.neu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.pojo.User;
import com.neu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

        HttpSession session = request.getSession();
        response.setContentType("application/json");

        try {
            // 直接从请求参数中获取用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 创建User对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // 执行登录操作
            User currentUser = userService.selectUserById(user);
            if (currentUser != null) {
                //登录成功
                session.setAttribute("user", user);
                response.getWriter().write(objectMapper.writeValueAsString("登录成功"));
            } else {
                // 登录失败
                response.getWriter().write(objectMapper.writeValueAsString("登录失败"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
            response.getWriter().write(objectMapper.writeValueAsString("发生错误"));
        }
    }
}
