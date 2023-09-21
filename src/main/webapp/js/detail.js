addEventListener('load', loadMovieInfo);
window.onload = function() {
    console.log("enter")
    loadMovieInfo();

}
function loadMovieInfo(){
    console.log("enter1")
    let currentUrl = window.location.href;

// 从 URL 中解析出参数
    let urlParams = new URLSearchParams(window.location.search);
    let movie = urlParams.get('movie');
    movie = JSON.parse(decodeURIComponent(movie));
    console.log(movie)

    $("#movie-name").text(movie.name);
    $("#time").text("Released Time: "+movie.year);
    let category = "";
    for(let i = 0; i <= movie.type.length; i++){
        category += movie.type[i]+", ";
    }
    $("#category").text("Category: "+movie.typeInfo);

    //rate
    if(movie.rate != 0){
        starRank();
    }
}


//star show
let starContainer1 = document.querySelectorAll(".star-container1");
function starRank(){
    let rank = 4;//movie's rank
    //set the star active or inactive
    for (let i = 0; i <= rank; i++) {
            starContainer1[i].classList.add("active");
            starContainer1[i].classList.remove("inactive");
            starContainer1[i].firstElementChild.className = "fa-star fa-solid";
    }
};


function buttonSubmit(){
    $("#submit").addEventListener("click", () => {
        $("#submit-section").classList.remove("hide");
        $("#submit-section").classList.add("show");
        $("#submit").disabled = true;


        //calculate the active star
        $.ajax({
            url: "http://localhost:8080/rating",
            method: "post",
            contentType: "application/json",
            success: (data) => {


            },
            error: (xhr, status, error) => {
                alert("Error: " + error);
            }
        })
    });
}