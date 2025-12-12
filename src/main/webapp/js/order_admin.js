/**
 * Xử lý sự kiện click trên các hàng có class 'clickable-row'
 * để chuyển hướng đến URL được lưu trữ trong thuộc tính 'data-href'.
 */
document.addEventListener('DOMContentLoaded', function() {
    const rows = document.querySelectorAll('.clickable-row');

    rows.forEach(row => {
        row.addEventListener('click', function(event) {
            // Lấy URL từ thuộc tính data-href của hàng
            const detailUrl = this.getAttribute('data-href');

            // Kiểm tra xem sự kiện click có phải xảy ra trên checkbox hoặc action menu icon không
            // Chúng ta không muốn chuyển hướng nếu người dùng đang cố gắng tương tác với các thành phần này.
            const target = event.target;

            // Kiểm tra nếu click vào checkbox hoặc bất kỳ phần tử con nào của nó
            if (target.matches('input[type="checkbox"]') || target.closest('input[type="checkbox"]')) {
                // Cho phép hành vi mặc định của checkbox
                return;
            }

            // Kiểm tra nếu click vào action menu icon hoặc bất kỳ phần tử con nào của nó
            if (target.matches('.action-menu') || target.closest('.action-menu')) {
                // Ở đây bạn có thể thêm logic để hiển thị menu hành động thay vì chuyển hướng
                return;
            }

            // Nếu có URL và không click vào checkbox/action menu, thực hiện chuyển hướng
            if (detailUrl) {
                window.location.href = detailUrl;
            }
        });
    });
});