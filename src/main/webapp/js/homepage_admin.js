// xử lý khi ấn nút user
function toggleUserMenu() {
    document.getElementById("userMenuContent").classList.toggle("show");
}

// Đóng menu nếu người dùng click ra ngoài
window.onclick = function(event) {
    if (!event.target.matches('.user-logo')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}


function openProductModal(isEdit = false) {
    const modal = document.getElementById('product-modal');
    const modalTitleSpan = document.getElementById('modal-title-product');
    const editButtons = document.querySelectorAll('.product-table .edit-btn');
    editButtons.forEach(button => {
        button.addEventListener('click', () => openProductModal(true));
    });

    if (isEdit) {
        modalTitleSpan.textContent = 'Sửa';

    } else {
        modalTitleSpan.textContent = 'Thêm';
        // Đảm bảo form được reset khi thêm mới
        modal.querySelector('.product-form').reset();
    }

    modal.style.display = 'flex';
}

function closeProductModal() {
    document.getElementById('product-modal').style.display = 'none';
}

