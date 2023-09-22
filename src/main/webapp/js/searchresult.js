function search() {
    var searchMessage = document.getElementById("search-text").value;

    $.ajax({
        url: "http://localhost:8080/search",
        method: "post",
        data: JSON.stringify({
            searchMessage:searchMessage,
        }),
        contentType: "application/json",
        success: (data) => {
            window.location.href="http://localhost:8889/searchresult.jsp"
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })
}

function starSet(){
    let starContainer = document.querySelectorAll(".star-container");
    for (let i = 0; i <= end; i++) {
            starContainer[i].classList.add("active");
            starContainer[i].classList.remove("inactive");
            starContainer[i].firstElementChild.className = "fa-star fa-solid";
    }
}