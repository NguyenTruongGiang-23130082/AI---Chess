document.addEventListener("DOMContentLoaded", () => {
    const editPopup = document.getElementById("edit-popup");
    const passwordPopup = document.getElementById("password-popup");
    const editBtn = document.querySelector(".edit-btn");
    const passBtn = document.querySelector(".pass-btn");
    const cancelBtns = document.querySelectorAll(".cancel-btn");

    // 👉 Mở popup chỉnh sửa thông tin
    editBtn.addEventListener("click", () => {
        editPopup.classList.add("show");
    });

    // 👉 Mở popup đổi mật khẩu
    passBtn.addEventListener("click", () => {
        passwordPopup.classList.add("show");
    });

    // 👉 Đóng popup khi nhấn nút "Hủy"
    cancelBtns.forEach(btn => {
        btn.addEventListener("click", () => {
            editPopup.classList.remove("show");
            passwordPopup.classList.remove("show");
        });
    });

    // 👉 Đóng popup khi click ra ngoài nội dung
    window.addEventListener("click", (e) => {
        if (e.target === editPopup || e.target === passwordPopup) {
            editPopup.classList.remove("show");
            passwordPopup.classList.remove("show");
        }
    });
});
