// 获取图像元素
const moviePoster = document.getElementById('movie-poster-1');

// 定义详细信息页的 URL
const detailedPageUrl = 'http://localhost:8889/NEU_Douban_war/detail.html';

// 添加点击事件处理程序
moviePoster.addEventListener('click', function() {
    // 在点击时跳转到详细信息页
    const imageUrl = moviePoster.getAttribute('src');
    window.location.href = detailedPageUrl+"?image=${encodeURIComponent(imageUrl)}";
});