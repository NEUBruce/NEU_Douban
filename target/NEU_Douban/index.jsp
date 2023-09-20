<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="./css/index.css"/>
    <title>电影推荐</title>
</head>

<body>
<div class="container">
    <h1 style="text-align: center;">电影推荐</h1>
    <form action="RecomendServlet" method="post">
        <label for="userID">用户ID</label>
        <input type="text" name="userID" id="userID">

        <label for="size">推荐数目</label>
        <input type="text" name="size" id="size" value="25">

        <label>推荐类型</label>
        <input type="radio" name="recommendType" checked="checked" value="userBased"> User Based
        <input type="radio" name="recommendType" value="itemBased"> Item Based
        <input type="radio" name="recommendType" value="slopeOne"> Slope One

        <div style="text-align: center; margin-top: 20px;">
            <input type="submit" value="提交">
        </div>
    </form>
</div>
</body>
</html>
