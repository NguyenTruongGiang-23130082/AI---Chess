function openCategoryModal(mode = 'add', id = '', name = '') {
    const modal = document.getElementById('categoryModal');
    const title = document.getElementById('modalTitle');
    const categoryNameInput = document.getElementById('categoryName');
    const categoryIdInput = document.getElementById('categoryId');
    // const categorySlugInput = document.getElementById('categorySlug'); // Nếu bạn muốn dùng slug
    const submitBtn = modal.querySelector('.submit-btn');

    if (mode === 'edit') {
        title.textContent = 'Chỉnh Sửa Danh Mục (ID: ' + id + ')';
        categoryNameInput.value = name;
        categoryIdInput.value = id;
        submitBtn.textContent = 'Cập Nhật Danh Mục';
    } else {
        title.textContent = 'Thêm Danh Mục Mới';
        categoryNameInput.value = '';
        categoryIdInput.value = '';
        submitBtn.textContent = 'Lưu Danh Mục';
    }
    modal.style.display = 'block';
}

// Hàm đóng Modal
function closeCategoryModal() {
    document.getElementById('categoryModal').style.display = 'none';
}

// Hàm xác nhận xóa
function confirmDelete(id, name) {
    if (confirm(`Bạn có chắc chắn muốn xóa danh mục "${name}" (ID: ${id})? Hành động này không thể hoàn tác.`)) {
        alert(`Đã thực hiện xóa danh mục ${name}.`);
        // Ở đây sẽ có code AJAX để gọi API xóa danh mục
    }
}

// Xử lý đóng modal khi click ra ngoài và xử lý submit form
document.addEventListener('DOMContentLoaded', function() {
    const categoryModal = document.getElementById('categoryModal');
    const categoryForm = document.getElementById('categoryForm');

    // Đóng modal khi click ra ngoài
    window.onclick = function(event) {
        if (event.target === categoryModal) {
            closeCategoryModal();
        }
    }

    // Xử lý submit form (mô phỏng)
    if (categoryForm) {
        categoryForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const id = document.getElementById('categoryId').value;
            const name = document.getElementById('categoryName').value;

            if (id) {
                alert(`Đã cập nhật danh mục ID ${id} thành "${name}".`);
            } else {
                alert(`Đã thêm danh mục mới: "${name}".`);
            }
            closeCategoryModal();
        });
    }
});
