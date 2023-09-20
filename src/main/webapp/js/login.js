function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // 在这里可以添加登录逻辑，例如发送请求到服务器验证用户名和密码

    alert("登录成功！"); // 仅作为示例
}

function register() {
    window.location.href="register.jsp";
}