package com.neu.web;

import com.neu.pojo.Movie;
import com.neu.pojo.User;
import com.neu.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieServlet {
    private MovieService movieService=new MovieService();

    public void addMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String strId = request.getParameter("id");
        Integer id = Integer.parseInt(strId);
        String name = request.getParameter("name");
        String year = request.getParameter("year");
        String strType = request.getParameter("type");
        String substring = strType.substring(0, strType.length() - 1);
        String[] split = substring.split(",");
        List<String> type = Arrays.asList(split);

        Movie movie = new Movie(id,name,year,type);
        movieService.addMovie(movie);
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
