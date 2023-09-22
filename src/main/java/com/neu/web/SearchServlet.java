package com.neu.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.mapper.MovieMapper;
import com.neu.pojo.Movie;
import com.neu.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private MovieService movieService=new MovieService();
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
        // 获取用户输入的搜索信息
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // 解析JSON以获取searchMessage
        JsonNode jsonNode = objectMapper.readTree(json.toString());
        String searchMessage = jsonNode.get("searchMessage").asText();

        // 调用searchMovie方法执行查询
        List<Movie> searchResult = movieService.searchMovie(searchMessage);
        // 对searchResult中的rate属性进行四舍五入处理
        for (Movie movie : searchResult) {
            double rate = movie.getRate();
            int roundedRate = (int) Math.round(rate);
            movie.setRate(roundedRate);
        }

        // 将查询结果传递给JSP页面进行显示
        session.setAttribute("searchResult", searchResult);

        //页面转发
        request.getRequestDispatcher("searchresult.jsp").forward(request, response);
    }
}
