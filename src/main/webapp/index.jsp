<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HOME DECOR - TRANG CHỦ</title>
    <link rel="icon" type="image/png"  href="img/logo.png" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="css/homepage_style.css">
    <link
            href="https://cdn.jsdelivr.net/npm/remixicon@4.7.0/fonts/remixicon.css"
            rel="stylesheet"
    />



</head>
<body>
<div id="header">
    <div id="logo"><img src="img/logo.png" class="image" alt=""/>
        <div class="brand"> <div id="name-web">HOME DECOR</div>
            <div id="sub-slogan">Nét mộc trong từng góc nhỏ</div></div>
    </div>

    <nav class="menu-bar">

        <a class="menu" id="home" href="homepage_user.jsp"> TRANG CHỦ</a>
        <div class="menu product-menu">
            <a id="product" href="product_all_user.html">SẢN PHẨM</a>
            <div class="submenu">
                <a href="decorate_livingroom_user.html">TRANG TRÍ PHÒNG KHÁCH</a>
                <a href="decorate_bedroom_user.html">TRANG TRÍ PHÒNG NGỦ</a>
                <a href="decorate_kitchen_user.html">TRANG TRÍ PHÒNG BẾP</a>
                <a href="decorate_homeoffice_user.html">TRANG TRÍ PHÒNG LÀM VIỆC</a>
                <a href="decorate_miniitem_user.html">ĐỒ TRANG TRÍ MINI</a>
                <a href="souvenirs_user.html">QUÀ LƯU NIỆM</a>
            </div>
        </div>
        <a class="menu" id=" " href="purchasing_policy_user.html" >CHÍNH SÁCH MUA HÀNG</a>
        <a class="menu" id="introduce" href="introduce_user.html" >GIỚI THIỆU</a>
        <a class="menu" id="contact" href="contact_user.html">LIÊN HỆ</a>

    </nav>
    <div class="icons">
        <a class="nav_item" href="shopping-cart.html" id="cart-link">
            <i class="fas fa-shopping-cart"></i>
        </a>

        <a class="nav-item" href="search.html" id="search-link">
            <i class="fas fa-search"></i>
        </a>

        <div class="user-login">
            <i class="fas fa-user"></i>
            <div class="user">
                <a class="nav_item" href="mypage_user.html" id="myPage">Trang của tôi</a>
                <a class="nav-item" href="login.jsp" id="login-register">Đăng nhập/Đăng ký</a>
            </div>

        </div>
    </div>



</div>
<div class="banner">
    <button class="pause-play-btn" id="pausePlayBtn">
        <i class="fas fa-pause"></i>
    </button>
    <div class="indicator" id="indicator1">
        <div class="progress-loader" >

            <div class="progress"></div>
        </div>
        <div class="progress-loader" >

            <div class="progress"></div>
        </div>
        <div class="progress-loader" >

            <div class="progress"></div>
        </div>
        <div class="progress-loader" >

            <div class="progress"></div>
        </div>
    </div>
    <div class="banner-container">
        <div class="banner-slide active" id="slide-1"
             style="background-image: url('https://i.pinimg.com/1200x/c1/9d/df/c19ddf496eadac9b51cfd9c56c719686.jpg');" >
            <div class="overlay"></div>

        </div>

        <div class="banner-slide" id="slide-2" style="background-image: url('https://woodbests.com/cdn/shop/files/banner_02_1.png?v=1757746115&width=1920');">
            <div class="overlay"></div>

        </div>

        <div class="banner-slide" id="slide-3" style="background-image: url('https://images.pexels.com/photos/4050318/pexels-photo-4050318.jpeg');">
            <div class="overlay"></div>

        </div>
        <div class="banner-slide" id="slide-4" style="background-image: url('https://images.pexels.com/photos/6739226/pexels-photo-6739226.jpeg');">
            <div class="overlay"></div>

        </div>


    </div>
    <div class="banner-slogan">
        <h2>Nơi gỗ kể câu chuyện của không gian.</h2>
        <p>Từng đường vân, sắc màu và cảm hứng từ thiên nhiên được chúng tôi tỉ mỉ chọn lọc và chăm chút, mang đến cảm giác ấm áp, tinh tế và gần gũi – tạo nên không gian hài hòa, chan hòa với thiên nhiên.</p>
    </div>

    <section class="products">
        <div class="product-card">
            <!--            <span class="badge">NEW</span>-->
            <a href="product_details_user.html">
                <img src="https://i.pinimg.com/736x/0b/e6/ab/0be6ab843569781d6a78bd3786e736de.jpg" alt="ke treo tuong">
                <h2>Kệ treo tường</h2>
            </a>
            <div class="rating">
            </div>
            <div class="price">129.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--            <span class="badge">NEW</span>-->
            <img src="https://i.pinimg.com/736x/68/1c/46/681c469884d60a1a27b0b3686c589f3e.jpg" alt="Bộ bàn ghế gỗ tự nhiên">
            <h2>Bộ bàn ghế gỗ tự nhiên</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <span>(5.0)</span></div>
            <div class="price">99.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--            <span class="badge">NEW</span>-->
            <img src="https://i.pinimg.com/736x/29/1c/81/291c814c237f586afa66fb1700800563.jpg" alt="Đồ trang trí tường">
            <h2>Đồ trang trí tường bằng gỗ</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-half-line"></i>
                <span>(4.5)</span></div>
            <div class="price">159.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>
    </section>

    <section class="intro-section">
        <div class="intro-content">
            <div class="intro-text">
                <h2>An tâm mua sắm với chính sách hỗ trợ toàn diện</h2>
                <p>Chúng tôi luôn đặt lợi ích khách hàng lên hàng đầu. Tìm hiểu về Chính sách Bảo hành, Vận chuyển và Đổi trả để yên tâm lựa chọn sản phẩm ưng ý.</p>
                <button class="intro-btn">Tìm hiểu ngay</button>
            </div>

            <div class="intro-image">
                <img src="https://i.pinimg.com/1200x/f3/01/41/f3014120ee3158232a4285f3695663c1.jpg" alt="sp">
                <!--                <div class="image-number">1</div>-->
            </div>
        </div>
    </section>

    <button class="nav-btn prev-btn">❮</button>
    <button class="nav-btn next-btn">❯</button>
</div>


<section class="san-pham-moi">
    <h2 class="title">SẢN PHẨM NỔI BẬT</h2>
    <p class="section-desc">Khám phá những thiết kế tinh tế và hiện đại, mang đến vẻ đẹp mộc mạc cho không gian sống của bạn.</p>
    <div class="section-btn">
        <a href="#" class="view-all-btn">Xem tất cả</a>
    </div>
    <div class="san-pham-nb">
        <div class="product-card">
            <!--                <span class="badge">HOT</span>-->
            <a href="product_details_user.html">
                <img src="https://i.pinimg.com/736x/0b/e6/ab/0be6ab843569781d6a78bd3786e736de.jpg" alt="ke treo tuong">
                <h2>Kệ treo tường</h2>
            </a>
            <div class="rating">
            </div>
            <div class="price">129.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--                <span class="badge">HOT</span>-->
            <img src="https://i.pinimg.com/736x/68/1c/46/681c469884d60a1a27b0b3686c589f3e.jpg" alt="Bộ bàn ghế gỗ tự nhiên">
            <h2>Bộ bàn ghế gỗ tự nhiên</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <span>(5.0)</span></div>
            <div class="price">99.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--                <span class="badge">HOT</span>-->
            <img src="https://i.pinimg.com/736x/29/1c/81/291c814c237f586afa66fb1700800563.jpg" alt="Đồ trang trí tường">
            <h2>Đồ trang trí tường bằng gỗ</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-s-fill"></i>
                <i class="ri-star-half-line"></i>
                <span>(4.5)</span></div>
            <div class="price">159.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button>
                <button class="buy-now">Mua hàng</button>
            </div>
        </div>
        <div class="product-card">
            <img src="https://i.pinimg.com/1200x/e2/98/df/e298df4cc098f4e33e0a69337a313ca6.jpg" alt="Đèn ngủ gỗ mộc">
            <h2>Đèn ngủ gỗ mộc</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><span>(4.0)</span>
            </div>
            <div class="price">289.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button><button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <img src="https://i.pinimg.com/1200x/f4/64/92/f46492b39a263ce310e55a97b7f6842d.jpg" alt="Tủ gỗ nhỏ mini">
            <h2>Tủ gỗ nhỏ mini</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-half-line"></i><span>(4.5)</span>
            </div>
            <div class="price">499.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button><button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--                    <span class="badge">SALE</span>-->
            <img src="https://i.pinimg.com/1200x/60/bf/2c/60bf2c1fa64232cdffef2be1d3f6d4cc.jpg" alt="Giá để cây gỗ">
            <h2>Giá để cây gỗ</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-half-line"></i><span>(4.5)</span>
            </div>
            <div class="price">259.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button><button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <img src="https://i.pinimg.com/736x/d7/81/8b/d7818b9a2ec984047cb13b4f49ed5514.jpg" alt="Khung ảnh gỗ decor">
            <h2>Khung ảnh gỗ decor</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><span>(4.0)</span>
            </div>
            <div class="price">89.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button><button class="buy-now">Mua hàng</button>
            </div>
        </div>

        <div class="product-card">
            <!--                    <span class="badge">SALE</span>-->
            <img src="https://i.pinimg.com/1200x/39/15/f4/3915f401ae9ec92742b570155b9a8cfe.jpg" alt="Khay gỗ trang trí">
            <h2>Khay gỗ trang trí</h2>
            <div class="rating">
                <i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-s-fill"></i><i class="ri-star-half-line"></i><span>(4.5)</span>
            </div>
            <div class="price">179.000 VNĐ</div>
            <div class="action-buttons">
                <button class="add-cart">Thêm giỏ hàng</button><button class="buy-now">Mua hàng</button>
            </div>
        </div>

    </div>
</section>

<!--<section class="blog-section">-->
<!--    <div class="blog-header">-->
<!--        <h2>Bài Viết Gần Đây</h2>-->
<!--        <p>Khám phá những câu chuyện, mẹo trang trí và cảm hứng thiết kế nội thất mới nhất từ WIS Decor.</p>-->
<!--    </div>-->

<!--    <div class="blog-slider">-->
<!--        <div class="blog-card">-->
<!--            <img src="https://images.pexels.com/photos/1866149/pexels-photo-1866149.jpeg" alt="Decor Chair">-->
<!--            <div class="blog-info">-->
<!--                <h3>Ghế Mộc Tinh Tế Cho Không Gian Tối Giản</h3>-->
<!--                <p>Chiếc ghế đơn giản nhưng mang lại điểm nhấn tinh tế cho căn phòng của bạn.</p>-->
<!--            </div>-->
<!--        </div>-->

<!--        <div class="blog-card">-->
<!--            <img src="https://images.pexels.com/photos/1571460/pexels-photo-1571460.jpeg" alt="Living Room">-->
<!--            <div class="blog-info">-->
<!--                <h3>Trang Trí Phòng Khách Với Tông Màu Ấm</h3>-->
<!--                <p>Sự kết hợp giữa gỗ tự nhiên và ánh sáng vàng mang lại cảm giác ấm áp, gần gũi.</p>-->
<!--            </div>-->
<!--        </div>-->

<!--        <div class="blog-card">-->
<!--            <img src="https://images.pexels.com/photos/1571463/pexels-photo-1571463.jpeg" alt="Bedroom Decor">-->
<!--            <div class="blog-info">-->
<!--                <h3>Phòng Ngủ Thư Giãn Với Chất Liệu Tự Nhiên</h3>-->
<!--                <p>Không gian nghỉ ngơi nhẹ nhàng, mang đậm hơi thở của thiên nhiên.</p>-->
<!--            </div>-->
<!--        </div>-->

<!--        <div class="blog-card">-->
<!--            <img src="https://images.pexels.com/photos/1457841/pexels-photo-1457841.jpeg" alt="Dining Room">-->
<!--            <div class="blog-info">-->
<!--                <h3>Góc Ăn Uống Ấm Cúng Cho Gia Đình</h3>-->
<!--                <p>Tạo cảm giác sum vầy bằng cách lựa chọn nội thất phù hợp với phong cách sống.</p>-->
<!--            </div>-->
<!--        </div>-->
<!--    </div>-->
<!--</section>-->

<section class="about-section">
    <div class="about-content">
        <div class="about-text">
            <h2>Về <span>HOME DECOR</span></h2>
            <h3>Không Gian Sống – Nơi Cảm Xúc Bắt Đầu</h3>
            <p>
                Home Decor mang đến giải pháp thiết kế nội thất độc đáo, kết hợp giữa phong cách hiện đại
                và tinh thần mộc mạc. Chúng tôi tin rằng mỗi món đồ gỗ đều kể một câu chuyện –
                câu chuyện về gu thẩm mỹ và dấu ấn riêng của bạn.
            </p>
            <div class="about-signature">
                <p><strong>Tran Thi Thuy Kieu</strong><br>Founder & CEO, HOME Decor</p>
            </div>
            <button class="about-btn">Tìm Hiểu Thêm</button>
        </div>

        <div class="about-images">
            <div class="img-top">
                <img src="https://images.pexels.com/photos/1571460/pexels-photo-1571460.jpeg" alt="Living room decor">
                <img src="https://images.pexels.com/photos/1457841/pexels-photo-1457841.jpeg" alt="Bedroom decor">
            </div>

            <div class="about-card">
                <div class="card-header">
                    <img src="https://i.pinimg.com/736x/ac/ee/d5/aceed58b2efee46f597864ed43a8c291.jpg" alt="user">
                    <div class="card-info">
                        <h4>Xuân Mai</h4>
                        <p>Khách hàng thân thiết</p>
                    </div>

                    <i class="ri-star-s-fill rating"></i><span>(4.9) </span>
                </div>
                <p>
                    “Tôi thật sự ấn tượng với sự tinh tế trong từng chi tiết.
                    Không gian trở nên ấm cúng và sang trọng hơn rất nhiều.”
                </p>
            </div>
        </div>
    </div>
</section>

<div class="footer">
    <div class="footer-container">

        <div class="footer-col">
            <h3>Về chúng tôi</h3>


            <p>HOME DECOR</p>

            <p><i class="fa-solid"></i> Địa chỉ: Trường Đại học Nông Lâm Thành phố Hồ Chí Minh</p>
            <p><i class="fa-solid"></i> Số điện thoại: 0944459364</p>
            <p><i class="fa-solid"></i> Email: 23130082@st.hcmuaf.edu.vn</p>

            <div class="social-icons">
                <a href="#"><i class="fab fa-facebook"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-tiktok"></i></a>
            </div>
        </div>


        <div class="footer-col">
            <h3>Chính sách</h3>
            <a href="introduce_user.html">Về tụi mình</a>
            <a href="purchasing_policy_user.html">Chính sách Thanh toán</a>
            <a href="purchasing_policy_user.html">Chính sách Giao hàng</a>
            <a href="purchasing_policy_user.html">Chính sách Đổi trả</a>
        </div>


        <div class="footer-col">
            <h3>Hỗ trợ khách hàng</h3>
            <a href="product_all_user.html">Tất cả sản phẩm</a>
        </div>


        <div class="footer-col">
            <h3>Liên kiết nhanh</h3>
            <a href="homepage_user.jsp">Trang chủ</a>
            <a href="purchasing_policy_user.html">Chính sách mua hàng</a>
            <a href="introduce_user.html">Giới thiệu</a>
            <a href="contact_user.html">Liên hệ</a>
        </div>
    </div>
</div>

</body>
<script src="js/homepage.js"></script>
</html>