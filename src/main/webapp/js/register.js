// 获取男性和女性单选按钮的元素
const maleRadioButton = document.getElementById('male');
const femaleRadioButton = document.getElementById('female');

// 添加点击事件监听器
maleRadioButton.addEventListener('click', function() {
    // 取消女性单选按钮的选中状态
    femaleRadioButton.checked = false;
});

femaleRadioButton.addEventListener('click', function() {
    // 取消男性单选按钮的选中状态
    maleRadioButton.checked = false;
});
