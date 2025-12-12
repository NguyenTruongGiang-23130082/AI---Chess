// Lấy các phần tử cần dùng
const container = document.getElementById('container');
const overlayBtn = document.getElementById('overlayBtn');
const overlayCon = document.getElementById('overlayCon');

// Khi click vào nút chính giữa
overlayBtn.addEventListener('click', () => {
    container.classList.toggle('right-panel-active');

    // Đổi vị trí của overlay
    if (container.classList.contains('right-panel-active')) {
        overlayCon.style.transform = 'translateX(-150%)';
    } else {
        overlayCon.style.transform = 'translateX(0)';
    }
});

// Khi click nút "Sign Up" ở overlay phải
document.querySelector('.overlay-right button').addEventListener('click', () => {
    container.classList.add('right-panel-active');
    overlayCon.style.transform = 'translateX(-150%)';
});

// Khi click nút "Sign In" ở overlay trái
document.querySelector('.overlay-left button').addEventListener('click', () => {
    container.classList.remove('right-panel-active');
    overlayCon.style.transform = 'translateX(0)';
});
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
// const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

// Logic nhỏ để giữ người dùng ở lại tab Đăng ký nếu đăng ký lỗi
// Biến jsMessRegister lấy từ attribute của Servlet truyền về
var jsMessRegister = '${MESS_REGISTER}';
if(jsMessRegister !== '') {
    container.classList.add("right-panel-active");
}
