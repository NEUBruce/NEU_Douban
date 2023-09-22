let movieInfo= [];
addEventListener('load', loadMovieInfo);
window.onload = function() {
    console.log("enter")
    loadMovieInfo();

}
function loadMovieInfo(){
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
    $("#time").text("Released Time: "+movie.year);
    let category = "";
    for(let i = 0; i <= movie.type.length; i++){
        category += movie.type[i]+", ";
    }
    $("#category").text("Category: "+movie.typeInfo);

    //rate
    if(movie.rate !== 0 && movie.rate !== null){
        starRank();
    }
    buttonSubmit();
}


//star show

function starRank(){
    //movie's rank
    // $("#average-score").text(movieInfo.rate);
    // let rank = Math.round(movieInfo.rate);
    console.log("star")
    let starContainer1 = document.querySelectorAll(".star-container1");
    let rank = 3;
    //set the star active or inactive
    for (let i = 0; i <= rank; i++) {
            starContainer1[i].classList.add("active");
            starContainer1[i].classList.remove("inactive");
            starContainer1[i].firstElementChild.className = "fa-star fa-solid";
            console.log("active")
    }
};


function buttonSubmit(){
    $("#submit").addEventListener("click", () => {
        $("#submit-section").classList.remove("hide");
        $("#submit-section").classList.add("show");
        $("#submit").disabled = true;


        //calculate the active star
        let rank = 0;
        for (let i = 0; i <= 4; i++) {
            if(starContainer1[i].classList.contains("active"))
            {
                rank ++;
            }
        }

        $.ajax({
            url: "http://localhost:8080/rating",
            method: "post",
            contentType: "application/json",
            param: {
                movieID: movieInfo.id,
                rating: rank
            },
            success: (data) => {
                alert("Submit Success!");

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
        url: "http://localhost:8889/search",
        method: "post",
        data: JSON.stringify({
            searchMessage:searchMessage,
        }),
        contentType: "application/json",
        success: (data) => {
            window.location.href="http://localhost:8889/detail.html"
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}