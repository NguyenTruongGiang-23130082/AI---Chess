<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%
    // 1. Kiểm tra session
    User user = (User) session.getAttribute("LOGGED_USER");

    // 2. Nếu chưa đăng nhập hoặc không phải Admin -> Đuổi về trang login
    if (user == null || !"Admin".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HOME DECOR - TỔNG QUAN</title>
    <link rel="icon" type="image/png"  href="img/logo.png" >
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="css/homepage_admin.css">
    <link rel="stylesheet" href="css/admin_profile_style.css">

</head>
<body>

<div class="admin-container">
    <header class="header">
        <div class="logo-placeholder">
            <img src="img/logo.png" alt="Logo Modern Homes">
            <p class="logo">HOME DECOR</p>
        </div>

        <div class="header-icons">

            <div class="gmail-dropdown">
                <i class="fa-solid fa-envelope gmail-icon"></i>

                <div id="gmailMenuContent" class="dropdown-content gmail-content">
                    <div class="dropdown-header">Gmail</div>
                    <p class="no-messages-text">Không có Gmail nào.</p>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="view-all-link">Mở Gmail</a>
                </div>
            </div>
            <div class="notification-dropdown">
                <i class="fa-solid fa-bell notification-icon"></i>

                <div id="notificationMenuContent" class="dropdown-content notification-content">
                    <div class="dropdown-header">Thông Báo Mới (5)</div>
                    <a href="#">Đơn hàng mới #1001</a>
                    <a href="#">Sản phẩm hết hàng</a>
                    <a href="#">Khách hàng mới đăng ký</a>
                    <a href="#">Đơn hàng #1005 vừa được hủy bỏ</a>
                    <a href="#">Cần duyệt 3 đánh giá sản phẩm mới</a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="view-all-link">Xem tất cả</a>
                </div>
            </div>

            <div class="user-dropdown">
                <i class="fas fa-user-circle user-logo" ></i>
                <span style="color: #333; font-weight: bold; margin-left: 5px;">
        <%= user.getUsername() %>
    </span>

                <div id="userMenuContent" class="dropdown-content">
                    <a href="admin_thong_tin_tai_khoan.html"> Thông tin tài khoản</a>
                    <a href="admin_doi_mat_khau.html"> Đổi mật khẩu</a>
                    <div class="dropdown-divider"></div>
                    <a href="${pageContext.request.contextPath}/LogoutServlet" class="logout-link"> Đăng xuất</a>
                </div>
            </div>
        </div>
    </header>
    <div class="main-wrapper">
        <aside class="sidebar">
            <nav class="sidebar-nav">
                <ul>
                    <li class="active"><a href="#">Tổng quan</a></li>
                    <li><a href="html/admin_products.html"> Sản phẩm</a></li>
                    <li><a href="html/admin_order.html"> Đơn hàng</a></li>
                    <li><a href="html/admin_customer.html"> Khách hàng</a></li>
                    <li><a href="html/admin_profile.html"> Hồ sơ</a></li>
                    <li><a href="html/admin_setting.html"> Cài đặt</a></li>
                </ul>
            </nav>
        </aside>


        <main class="content">
            <h2 class="page-title">Tổng quan tháng 11</h2>

            <div class="kpi-row-modern">
                <div class="kpi-card-modern">
                    <div class="kpi-label-modern">Tổng Doanh Thu <span class="trend up">▲ 12.2%</span></div>
                    <div class="kpi-value-modern">720,700,000 <span class="unit">VND</span></div>
                </div>
                <!--                <div class="kpi-card-modern">-->
                <!--                    <div class="kpi-label-modern">Tổng Lượt Truy Cập <span class="trend down">▼ 8.2%</span></div>-->
                <!--                    <div class="kpi-value-modern">320</div>-->
                <!--                </div>-->
                <div class="kpi-card-modern">
                    <div class="kpi-label-modern">Số Lượng Đơn Hàng <span class="trend up">▲ 4.5%</span></div>
                    <div class="kpi-value-modern">57</div>
                </div>
                <div class="kpi-card-modern">
                    <div class="kpi-label-modern">Khách Hàng Mới <span class="trend up">▲ 10.0%</span></div>
                    <div class="kpi-value-modern">231</div>
                </div>
            </div>

            <div class="widget-full-width">
                <div class="widget-card">
                    <table class="data-table-modern">
                        <thead>
                        <tr>
                            <th>Top</th>
                            <th>Sản Phẩm</th>
                            <th>Lượt Mua</th>
                            <!--                            <th>Còn Lại</th>-->
                            <!--                            <th>Số Lượt Xem</th>-->
                            <th>Đánh Giá</th>
                            <!--                            <th>Xu Hướng</th>-->
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Kệ Treo Tường Gỗ Sồi 3 Tầng</td>
                            <!--                            <td>530</td>-->
                            <!--                            <td>10</td>-->
                            <td>622</td>
                            <td>4.8 <i class="fas fa-star text-yellow"></i></td>
                            <!--                            <td><span class="trend up">▲ 4.5%</span></td>-->
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Đồng Hồ Treo Tường Gỗ Phong Cách Retro</td>
                            <!--                            <td>330</td>-->
                            <!--                            <td>30</td>-->
                            <td>422</td>
                            <td>4.8 <i class="fas fa-star text-yellow"></i></td>
                            <!--                            <td><span class="trend up">▲ 4.5%</span></td>-->
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Chậu Cây Để Bàn Gỗ Tần Bì (Set 3)</td>
                            <!--                            <td>230</td>-->
                            <!--                            <td>93</td>-->
                            <td>325</td>
                            <td>4.8 <i class="fas fa-star text-yellow"></i></td>
                            <!--                            <td><span class="trend up">▲ 4.5%</span></td>-->
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Tượng Gỗ Trang Trí Hình Hươu (Lớn)</td>
                            <!--                            <td>230</td>-->
                            <!--                            <td>40</td>-->
                            <td>322</td>
                            <td>4.8 <i class="fas fa-star text-yellow"></i></td>
                            <!--                            <td><span class="trend up">▲ 4.5%</span></td>-->
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</div>

<script src="js/homepage_admin.js"></script>
</body>
</html>
