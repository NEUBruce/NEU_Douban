window.onload = function() {
    addFriendListener();
    friendRecommend();
}
let friends=[]
function addFriendListener(){
    const buttons = document.getElementsByClassName('add-button');
    let isToggled = false;

    Array.from(buttons).forEach(function(button,index) {
        button.addEventListener('click', () => {
            if (isToggled) {
                button.style.backgroundColor = "rgb(31, 185, 206)"; // 恢复原始颜色
                button.textContent = 'Add'; // 恢复原始文本

                //ajax
                let param ={

                }
                $.ajax({
                    url: "http://localhost:8080/addFriends",
                    method: "post",
                    contentType: "application/json",
                    success: (data) => {


                    },
                    error: (xhr, status, error) => {
                        alert("Error: " + error);
                    }
                })
            } else {
                button.style.backgroundColor = 'rgb(241,116,116)'; // 设置为红色背景
                button.textContent = 'Cancel'; // 设置文本为 "Cancel"

                $.ajax({
                    url: "http://localhost:8080/delFriends",
                    method: "post",
                    contentType: "application/json",
                    success: (data) => {


                    },
                    error: (xhr, status, error) => {
                        alert("Error: " + error);
                    }
                })
            }

            isToggled = !isToggled; // 切换状态
        });
    });


}

function friendRecommend(){

    $.ajax({
        url: "http://localhost:8080/friendRecommendServlet",
        method: "get",
        contentType: "application/json",
        success: (data) => {
            console.log(data)
            friends = data;
            //fill
            for (let i = 1; i <= 4; i++) {
                let friend = data[i - 1];
                $("#person-label-" + i).text(friend.name);
            }
        },
        error: (xhr, status, error) => {
            // 处理 AJAX 请求失败的情况
            alert("Error: " + error);
        }
    })


}
