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
        System.out.println("1111");
        try {
            // 从请求体中获取JSON数据
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            // 解析JSON以获取用户名和密码
            JsonNode jsonNode = objectMapper.readTree(json.toString());
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();

            // 创建User对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // 执行登录操作
            User currentUser = userService.selectUserByName(user);
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
