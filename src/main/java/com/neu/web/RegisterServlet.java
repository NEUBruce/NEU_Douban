package com.neu.web;

import com.alibaba.fastjson.JSON;
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

/**
 * 注册功能
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();
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
            String gender = jsonNode.get("gender").asText();
            String strAge = jsonNode.get("age").asText();
            Integer age = Integer.parseInt(strAge);
            String zipCode = jsonNode.get("zipCode").asText();
            String vocation = jsonNode.get("vocation").asText();

            // 创建User对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setGender(gender);
            user.setAge(age);
            user.setZipCode(zipCode);
            user.setVocation(vocation);

            // 检查用户名是否已存在
            User isExist = userService.selectUserByName(user);
            if (isExist!=null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Username already exists");
                return;
            }

            //注册用户
            int result = userService.insertUser(user);
            if(result!=0){
                // 注册成功
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\": \"Successfully registered\"}");
            }
            else{
                // 注册失败
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"message\": \"Registration failed\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}