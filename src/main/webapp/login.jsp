<jsp:useBean id="user" scope="session" type="com.neu.pojo.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="com.neu.pojo.User" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/login.css">
    <title>Login</title>
</head>
<body>
<div class="header">
    Movie Recommendation
</div>
<div class="content-container">
    <div class="login-container">
        <h2>Login</h2>
        <form th:action="@{/login}" method="post" th:object="com.neu.pojo.User">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" placeholder="Please input your username" th:value="user.username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" placeholder="Please input your password" th:value="user.password">
            </div>
            <div class="btn-container">
                <button class="btn1" onclick="login()">Login</button>
                <button class="btn2" onclick="register()">Register</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
