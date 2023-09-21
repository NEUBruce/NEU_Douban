function refresh_recommend(){

    $.ajax({
        url: "http://localhost:8080/movieRecommendServlet",
        method: "get",
        contentType: "application/json",
        success: (data) => {
            console.log(data)
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })


}