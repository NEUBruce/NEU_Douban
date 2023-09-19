package com.neu.web;

import com.neu.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet("/login")
public class UserServlet extends ViewBaseServlet{
    private UserService userService=new UserService();

    public void login(HttpServletRequest request, HttpServletResponse response){

    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
    }

}
