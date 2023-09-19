<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/login.css">
    <title>Login</title>
</head>
<body>
<div class="header">
    Movie recommendation
</div>
<div class="content-container">
    <div class="login-container">
        <h2>Login</h2>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" placeholder="Please input your username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" placeholder="Please input your password">
        </div>
        <div class="btn-container">
            <button class="btn" onclick="login()">Login</button>
            <button class="btn" onclick="register()">Register</button>
        </div>
    </div>
</div>
</body>
</html>
