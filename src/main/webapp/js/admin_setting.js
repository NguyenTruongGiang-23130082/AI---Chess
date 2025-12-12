// admin_setting.js - Phiên bản Tối Ưu Hóa Cuộn Đến Đáy Trang

document.addEventListener('DOMContentLoaded', function () {
    // Lấy các phần tử cần thiết
    const sections = document.querySelectorAll('.settings-card');
    const navItems = document.querySelectorAll('.settings-menu a');
    const headerElement = document.querySelector('.header');

    // Tính toán chiều cao offset động (header cố định + khoảng cách an toàn)
    const dynamicHeaderHeight = headerElement ? headerElement.offsetHeight + 10 : 70;

    let isScrollingFrame;
    let updateTimer;

    // Hàm 1: Gỡ bỏ trạng thái active
    function removeActiveClass() {
        navItems.forEach(item => {
            item.closest('li').classList.remove('active');
        });
    }

    // Hàm 2: Logic chính để cập nhật trạng thái active
    function updateActiveSection() {
        // --- Bắt đầu Kiểm tra Đáy Trang ---
        // Độ cao cuộn hiện tại + Chiều cao màn hình phải bằng Chiều cao toàn bộ trang
        const isScrolledToBottom = (window.innerHeight + window.scrollY) >= (document.documentElement.scrollHeight - 5); // -5 là sai số nhỏ

        if (isScrolledToBottom) {
            // Nếu ở đáy trang, BẮT BUỘC mục cuối cùng phải active
            removeActiveClass();
            if (navItems.length > 0) {
                navItems[navItems.length - 1].closest('li').classList.add('active');
            }
            return; // Thoát ngay, không cần kiểm tra các mục khác
        }
        // --- Kết thúc Kiểm tra Đáy Trang ---

        // Mặc định là mục đầu tiên
        let currentActiveSectionId = sections[0].getAttribute('id');

        // Lặp ngược để ưu tiên mục gần đỉnh viewport nhất
        for (let i = sections.length - 1; i >= 0; i--) {
            const section = sections[i];
            const rect = section.getBoundingClientRect();

            // Mục active nếu đỉnh của nó đã vượt qua điểm offset (ngay dưới header)
            if (rect.top <= dynamicHeaderHeight) {
                currentActiveSectionId = section.getAttribute('id');
                break; // Tìm thấy mục gần nhất, thoát vòng lặp
            }
        }

        // Cập nhật menu nếu chưa ở đáy trang
        removeActiveClass();
        const activeLink = document.querySelector(`.settings-menu a[href="#${currentActiveSectionId}"]`);
        if (activeLink) {
            activeLink.closest('li').classList.add('active');
        }
    }

    // --- Xử lý sự kiện Cuộn (Chỉ cập nhật khi cuộn dừng) ---
    updateActiveSection(); // Khởi tạo lần đầu

    window.addEventListener('scroll', function () {
        // Tối ưu hóa hiệu suất bằng requestAnimationFrame
        window.cancelAnimationFrame(isScrollingFrame);
        isScrollingFrame = window.requestAnimationFrame(updateActiveSection);

        // Logic "debounce" (chỉ cập nhật sau khi cuộn dừng)
        clearTimeout(updateTimer);
        updateTimer = setTimeout(function () {
            updateActiveSection();
        }, 100);
    });


    // --- Xử lý sự kiện Nhấp chuột ---
    navItems.forEach(item => {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                // Đảm bảo mục vừa nhấp được active ngay lập tức
                removeActiveClass();
                this.closest('li').classList.add('active');

                // Cuộn mượt mà
                window.scrollTo({
                    top: targetElement.offsetTop - dynamicHeaderHeight,
                    behavior: 'smooth'
                });

                // Sau khi cuộn kết thúc (500ms), buộc cập nhật lại trạng thái
                setTimeout(() => {
                    updateActiveSection();
                }, 500);
            }
        });
    });
});