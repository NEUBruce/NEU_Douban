<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/login.css">
    <title>用户登录</title>
</head>
<body>
<div class="login-container">
    <h2>用户登录</h2>
    <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" placeholder="请输入用户名">
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" placeholder="请输入密码">
    </div>
    <div class="btn-container">
        <button class="btn" onclick="login()">登录</button>
        <button class="btn" onclick="register()">注册</button>
    </div>
</div>
</body>
</html>
