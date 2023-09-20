function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // 登录逻辑，发送请求到服务器验证用户名和密码
    $.ajax({
        url: "http://localhost:8889/login",
        method: "post",
        data: JSON.stringify({
            username: username,
            password: password
        }),
        contentType: "application/json",
        success: (data) => {
            if (data.status === "success") {
                alert("Login Succeed!");
                window.location.href = "http://localhost:8889/index.html";
            } else {
                alert("Login Failed: " + data.message);
            }
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}

function register() {
    window.location.href="register.jsp";
}