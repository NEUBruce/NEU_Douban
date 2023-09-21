<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins&display=swap"
            rel="stylesheet"
    />
    <title>Movie</title>
    <link rel="stylesheet" type="text/css" href="css/searchresult.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">

</head>
<body>
<div class="navbar">

    <a href="mainpage.html" id="personal-center"></a>
    <a href="mainpage.html" id="Home">Home</a>

</div>

<div class="search-container">
    <h1>Movie Search</h1>
    <input type="text" id="search-text" class="search-text" placeholder="Please input search key word">
    <button class="search-button1"></button>
</div>
<div class="content-container">
    <c:forEach var="movie" items="${sessionScope.searchResult}">
    <div class="result">
        <img src="image/movie2.jpg" class="movie-photo">
        <div id="detail" class="detail">
            <h2 id="movie-name" class="movie-name">${movie.name}</h2>
            <div id="rank-box" class="rank-box">
                <h3 id="rank" class="rank">Average Rank: </h3>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
            </div>
            <h3 id="time" class="time">Released Time: ${movie.year}</h3>
            <h3 id="category" class="category">Category: ${movie.typeInfo}</h3>
        </div>
    </div>
    <div class="result">
        <img src="image/movie2.jpg" class="movie-photo">
        <div id="detail2" class="detail">
            <h2 id="movie-name2" class="movie-name">Auburnheimer</h2>
            <div id="rank-box2" class="rank-box">
                <h3 id="rank2" class="rank">Average Rank: </h3>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
            </div>
            <h3 id="time2" class="time">Released Time: 2023-9-21</h3>
            <h3 id="category2" class="category">Category: History</h3>
        </div>
    </div>
    <div class="result">
        <img src="image/movie2.jpg" class="movie-photo">
        <div id="detail3" class="detail">
            <h2 id="movie-name3" class="movie-name">Auburnheimer</h2>
            <div id="rank-box3" class="rank-box">
                <h3 id="rank3" class="rank">Average Rank: </h3>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
                <div class="star-container inactive">
                    <i class="fa-regular fa-star"></i>
                </div>
            </div>
            <h3 id="time3" class="time">Released Time: 2023-9-21</h3>
            <h3 id="category3" class="category">Category: History</h3>
        </div>
    </div>
    <div class="result"></div>
    <div class="result"></div>
    <div class="result"></div>
    </c:forEach>
</div>
<!-- Script -->
<script src="js/detail.js"></script>
<script src="js/star.js"></script>
</body>
</html>

