let movies = []

/*
* get the recommendation of movies
* */
function movie_recommend(){

    $.ajax({
        url: "http://localhost:8080/movieRecommendServlet",
        method: "get",
        contentType: "application/json",
        success: (data) => {
            console.log(data)
            movies = data;
            for (let i = 1; i <= 5; i++) {
                let movie = data[i - 1];
                $("#movie-label-" + i).text(movie.name);
                $("#movie-type-" + i).text(movie.typeInfo);
            }
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}


/*
* search function of main page
* */
function search() {
    var searchMessage = document.getElementById("search").value;

    $.ajax({
        url: "http://localhost:8080/search",
        method: "post",
        data: JSON.stringify({
            searchMessage:searchMessage,
        }),
        contentType: "application/json",
        success: (data) => {
            window.location.href="http://localhost:8080/searchresult.jsp"
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}

/*
* click and change to the detail page
* */
function clickChange(){
    const moviePosters = document.getElementsByClassName('movie');

// 定义详细信息页的 URL
    const detailedPageUrl = 'http://localhost:8080/detail.html';

    Array.from(moviePosters).forEach(function(moviePoster,index) {
        moviePoster.addEventListener('click', function() {
            // 在点击时跳转到详细信息页
            let jsonObj = JSON.stringify(movies[index]);
            window.location.href  = detailedPageUrl+"?movie=" + encodeURIComponent(jsonObj);
        });
    });
}