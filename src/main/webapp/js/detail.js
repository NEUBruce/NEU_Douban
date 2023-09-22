let movieInfo = [];
document.addEventListener("DOMContentLoaded", function() {
// 获取电影信息参数
    const movieName = getQueryParam("movie");
    const movieYear = getQueryParam("year");
    const movieRate = getQueryParam("rate");
    const movieTypeInfo = getQueryParam("typeInfo");
//更新页面信息
    document.getElementById("movie-name").textContent = movieName;
    document.getElementById("average-score").textContent = movieRate;
    document.getElementById("time").textContent = "Released Time: " + movieYear;
    document.getElementById("category").textContent = "Category: " + movieTypeInfo;
});

addEventListener('load', loadMovieInfo);
window.onload = function () {
    loadMovieInfo();
}

function loadMovieInfo() {
    console.log("enter1")
    // starRank();
    // buttonSubmit();
    let currentUrl = window.location.href;

// 从 URL 中解析出参数
    let urlParams = new URLSearchParams(window.location.search);
    let movie = urlParams.get('movie');
    movie = JSON.parse(decodeURIComponent(movie));
    console.log(movie);
    movieInfo = movie;

    $("#movie-name").text(movie.name);
    $("#time").text("Released Time: " + movie.year);
    let category = "";
    for (let i = 0; i <= movie.type.length; i++) {
        category += movie.type[i] + ", ";
    }
    $("#category").text("Category: " + movie.typeInfo);
    $("#average-score").text(movie.rate)
    //rate
    if (movie.rate !== 0 && movie.rate !== null) {
        starRank();
    }
    buttonSubmit(movie);
}


//star show

function starRank() {
    //movie's rank
    $("#average-score").text(movieInfo.rate);
    let rank = Math.round(movieInfo.rate);
    console.log("star")
    let starContainer1 = document.querySelectorAll(".star-container1");
    // let rank = 3;

    //set the star active or inactive
    for (let i = 0; i < rank; i++) {
        starContainer1[i].classList.add("active");
        starContainer1[i].classList.remove("inactive");
        starContainer1[i].firstElementChild.className = "fa-star fa-solid";
        console.log("active")
    }
};

function buttonSubmit(movie) {
    // 获取用户信息
    var userInfoElement = document.getElementById('user-info');
    var userJson = userInfoElement.getAttribute('data-user');
    var user = JSON.parse(userJson);

    // 获取其他表单数据
    var userId = document.getElementById('userId').value;
    var movieId = movie.movieId;
    var timestamp = new Date().getTime();

    console.log("button enter")
    let starContainer1 = document.querySelectorAll(".star-container1");
    let button = document.getElementById("submit");
    let submit_section = document.getElementById("submit-section");

    button.addEventListener("click", () => {
        // submit_section.classList.remove("hide");
        // submit_section.classList.add("show");
        button.style.display = "none";
        $("#submit").disabled = true;
        $("#message").text("Thanks for your ranting!")
        //calculate the active star
        let rank = 0;
        for (let i = 0; i <= 4; i++) {
            if (starContainer1[i].classList.contains("active")) {
                rank++;
            }
        }

        $.ajax({
            url: "http://localhost:8889/rating",
            method: "post",
            data: JSON.stringify({
                userId: userId,
                movieId: movieId,
                rank: rank,
                timestamp: timestamp,
            }),
            contentType: "application/json",
            data: {
                // movieID: movieInfo.id,
                movieID: 2,
                rating: rank
            },
            success: (data) => {
                alert("Submit Success!");
                console.log(rank)
            },
            error: (xhr, status, error) => {
                alert("Error: " + error);
            }
        })
    });
}

function search() {
    var searchMessage = document.getElementById("search-text").value;

    $.ajax({
        url: "http://localhost:8080/search",
        method: "post",
        data: JSON.stringify({
            searchMessage: searchMessage,
        }),
        contentType: "application/json",
        success: (data) => {
            window.location.href = "../detail.html"
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}

// 从 URL 中获取查询参数
function getQueryParam(parameterName) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(parameterName);
}

