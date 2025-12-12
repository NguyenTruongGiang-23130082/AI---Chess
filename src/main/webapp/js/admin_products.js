// Đợi cho đến khi toàn bộ HTML được tải
document.addEventListener('DOMContentLoaded', function() {
    // Lấy nút "Thêm Sản Phẩm Mới" bằng class của nó
    const addProductButton = document.querySelector('.add-new-product-btn');

    // Kiểm tra xem nút có tồn tại không
    if (addProductButton) {
        // Thêm trình lắng nghe sự kiện 'click' vào nút
        addProductButton.addEventListener('click', function() {
            // Thực hiện chuyển hướng đến trang thêm sản phẩm
            window.location.href = 'admin_add_products.html';
        });
    }
});