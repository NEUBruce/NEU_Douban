function movie_recommend(){

    $.ajax({
        url: "http://localhost:8080/movieRecommendServlet",
        method: "get",
        contentType: "application/json",
        success: (data) => {
            console.log(data)
            for (let i = 1; i <= 5; i++) {
                let movie = data[i - 1];
                $("#movie-label-" + i).text(movie.name);

            }
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })


}