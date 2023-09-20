package com.neu.web;

import com.neu.pojo.User;
import com.neu.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(2222);
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(111111111);
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 从请求的输入流中读取JSON数据并将其解析为User对象
            User user = objectMapper.readValue(request.getInputStream(), User.class);

            // 执行登录操作
            User currentUser = userService.selectUserById(user);
            if (currentUser != null) {
                //登录成功
                session.setAttribute("user", user);
                objectMapper.writeValue(response.getWriter(), "登录成功");
            } else {
                // 登录失败
                objectMapper.writeValue(response.getWriter(), "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
            objectMapper.writeValue(response.getWriter(), "发生错误");
        }
    }
}
