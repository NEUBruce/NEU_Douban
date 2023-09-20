function    login() {
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
        success: (data)=>{
            console.log(data);
            alert("Succeed!")
            window.location.href="http://localhost:8080/"
        }
    })
    return false
}

function register() {
    window.location.href="register.html";
}

function test() {
    $.ajax({
        url: "http://localhost:8080/testServlet"
    })
}