// 获取男性和女性单选按钮的元素
const maleRadioButton = document.getElementById('male');
const femaleRadioButton = document.getElementById('female');

// 添加点击事件监听器
maleRadioButton.addEventListener('click', function() {
    // 取消女性单选按钮的选中状态
    femaleRadioButton.checked = false;
});

femaleRadioButton.addEventListener('click', function() {
    // 取消男性单选按钮的选中状态
    maleRadioButton.checked = false;
});


function register() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var confirmPassword= document.getElementById("password-confirm").value;
    var selectedGender = document.querySelector('input[name="gender"]:checked').value;
    var age=document.getElementById("age").value;
    var zipCode=document.getElementById("zipCode").value;
    var selectedVocation = document.getElementById("vocation").value;

    if (password === confirmPassword) {
        // 构建一个包含用户信息的JSON对象
        var userData = {
            username: username,
            password: password,
            gender:selectedGender,
            age:age,
            zipCode:zipCode,
            vocation:selectedVocation,
        };

        // 使用AJAX将JSON数据发送到后端
        $.ajax({
            url: "http://localhost:8080/register",
            method: "POST",
            data: JSON.stringify(userData), // 将JSON对象转换为字符串
            contentType: "application/json",
            success: function(data) {
                if (data && data.message === "Successfully registered") {
                    alert("Registration successful. Redirecting to login page.");
                    // 成功注册后，重定向到登录页面
                    window.location.href = "http://localhost:8080/login.html";
                } else {
                    alert("Registration failed. Please try again.");
                }
            },
            error: function(xhr, status, error) {
                if (xhr.responseText) {
                    // 使用后端返回的自定义错误消息
                    alert("Registration failed. Error: " + xhr.responseText);
                } else {
                    // 没有自定义错误消息时，显示默认消息
                    alert("Registration failed. Error: " + error);
                }
            }
        });
    } else {
        // 密码不相同，弹出提示
        alert("Passwords do not match. Please try again.");
    }
}