<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="./css/register.css">
  <title>Register</title>
</head>
<body>
<div class="header">
  Movie Recommendation
</div>
<div class="content-container">
  <div class="login-container">
    <h2>Register</h2>
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="input" id="username" placeholder="Please input your username">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="input" id="password" placeholder="Please input your password">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="input" id="password-confirm" placeholder="Please confirm your password">
    </div>
    <div class="form-group">
      <label for="male">Gender:</label>
      <input type="radio" id="male" name="gender" value="male" ${gender == 'female' ? '' : 'checked'}>
      <label for="male">Male</label>
      <input type="radio" id="female" name="gender" value="female" ${gender == 'female' ? 'checked' : ''}>
      <label for="female">Female</label>
    </div>
    <div class="form-group">
      <label for="age">Age:</label>
      <input type="input" id="age" placeholder="Please input your age">
    </div>
    <div class="form-group">
      <label for="vocation">Vocation:</label>
      <select id="vocation">
        <option id="1">1</option>
        <option id="2">2</option>
        <option id="3">3</option>
        <option id="4">4</option>
      </select>
    </div>
    <div class="btn-container">
      <button class="btn" onclick="">Confirm</button>
    </div>
  </div>
</div>
</body>
</html>
