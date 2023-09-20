function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // 在这里可以添加登录逻辑，例如发送请求到服务器验证用户名和密码
    $.ajax({
        url: "http://localhost:80/login",
        method: "post",
        data: {
            username: username,
            password: password
        },
        success: (data)=>{
            let user = JSON.parse(data)
            if (user === null) {
                alert("Login in failed!")
            }else {
                alert("Succeed!")
            }

        }
    })
}

function register() {
    window.location.href="register.jsp";
}