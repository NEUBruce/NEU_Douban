function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // 登录逻辑，发送请求到服务器验证用户名和密码
    $.ajax({
        url: "http://localhost:8080/login",
        method: "post",
        data: JSON.stringify({
            username: username,
            password: password
        }),
        contentType: "application/json",
        dataType: "json",
        success: (data)=>{
            let user = JSON.parse(data)
            if (user === null) {
                alert("Login in failed!")
            }else {
                alert("Succeed!")
            }

        }
    })

    alert("登录成功！");
}

function register() {
    window.location.href="register.jsp";
}