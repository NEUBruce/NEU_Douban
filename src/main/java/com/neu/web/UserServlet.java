package com.neu.web;

import com.neu.pojo.User;
import com.neu.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/login")
public class UserServlet extends ViewBaseServlet {
    private UserService userService = new UserService();

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String vocation = request.getParameter("vocation");
        String zipCode = request.getParameter("zip_code");
        String strAge=request.getParameter("age");
        Integer age=Integer.parseInt(strAge);
        User user=new User(null,username, password,gender,vocation,zipCode,age);
        userService.insertUser(user);

        session.setAttribute("user", user);

        //重定向到login页面
        super.processTemplate("login",request,response);
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        HttpSession session = request.getSession() ;
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("userName");
        User user=new User();
        user.setUsername(username);
        User currentUser = userService.selectUserById(user);
        if(currentUser!=null){
            //登录成功，转到主页面
            session.setAttribute("user",user);
        }
        else{
            //登录失败，提示弹窗并刷新登录页面
        }
    }

    //增
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String vocation = request.getParameter("vocation");
        String zipCode = request.getParameter("zip_code");
        String strAge=request.getParameter("age");
        Integer age=Integer.parseInt(strAge);
        User user=new User(null,username, password,gender,vocation,zipCode,age);
        userService.insertUser(user);

        //重定向到login页面
        super.processTemplate("login",request,response);
    }

    //改
    public void modifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String vocation = request.getParameter("vocation");
        String zipCode = request.getParameter("zip_code");
        String strAge=request.getParameter("age");
        Integer age=Integer.parseInt(strAge);
        User user=new User(null,username, password,gender,vocation,zipCode,age);
        userService.modifyUser(user);

        //重定向到login页面
        super.processTemplate("login",request,response);
    }

    //删
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("userName");
        User user=new User();
        user.setUsername(username);
        userService.deleteUserByName(user);

        //重定向到login页面
        super.processTemplate("login",request,response);
    }

    //查
    public List<User> selectAllUser(HttpServletRequest request, HttpServletResponse response){
        return userService.selectAllUser();
    }

}
