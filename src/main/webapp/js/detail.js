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

/*
* show the detail Info of movie clicked
* */
function loadMovieInfo() {
    // 从 URL 中解析出参数
    let urlParams = new URLSearchParams(window.location.search);
    let movie = urlParams.get('movie');
    movie = JSON.parse(decodeURIComponent(movie));
    console.log(movie);
    movieInfo = movie;

    //set value of movie
    $("#movie-name").text(movie.name);
    $("#time").text("Released Time: " + movie.year);
    let category = "";
    for (let i = 0; i <= movie.type.length; i++) {
        category += movie.type[i] + ", ";
    }
    $("#category").text("Category: " + movie.typeInfo);
    $("#average-score").text(movie.rate)

    //set the rating of movie
    if(movie.rate !== 0 && movie.rate !== null){
        starRank();
    }
    //submit rating function
    buttonSubmit();
}


/*
* movie's rating score --> star
* */
function starRank(){
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

/*
* submit the rating score
* */
function buttonSubmit() {
    // 获取用户信息

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
            if(starContainer1[i].classList.contains("active"))
            {
                rank ++;
            }
        }

        console.log("rank: " + rank)
        $.ajax({
            url: "http://localhost:8080/rating",
            method: "post",
            data: JSON.stringify({
                movieId: movieInfo.id,
                rating: rank
            }),
            contentType: "application/json",
            success: (data) => {
                alert("Submit Success!");
            }
        })
    });
}

/*
* search function
* */
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
            window.location.href="http://localhost:8080/detail.html"
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}


/*
* 从 URL 中获取查询参数
* */
function getQueryParam(parameterName) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(parameterName);
}

