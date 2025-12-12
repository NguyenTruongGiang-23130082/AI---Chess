package controller;

import dao.UserDao;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nếu ai đó gõ trực tiếp đường dẫn /LoginServlet thì đẩy về trang login form
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Nhận dữ liệu
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2. Kiểm tra DB
        UserDao dao = new UserDao();
        User user = dao.checkLogin(email, password);

        // 3. Xử lý kết quả
        if (user == null) {
            // TRƯỜNG HỢP: Đăng nhập thất bại
            request.setAttribute("ERROR_MESSAGE", "Email hoặc mật khẩu không chính xác!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // Kiểm tra trạng thái tài khoản
            if (!"Active".equals(user.getStatus())) {
                request.setAttribute("ERROR_MESSAGE", "Tài khoản của bạn đã bị khóa!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // TRƯỜNG HỢP: Đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("LOGGED_USER", user); // Lưu phiên làm việc

            // Phân quyền: Admin về trang Admin, User về trang chủ User
            if ("Admin".equalsIgnoreCase(user.getRole())) {
                // redirect to the admin homepage at the webapp root
                response.sendRedirect(request.getContextPath() + "/admin_homepage.jsp");
            } else {
                // CHUYỂN HƯỚNG VỀ HOMEPAGE USER
                response.sendRedirect(request.getContextPath() + "/homepage_user.jsp");
            }
        }
    }
}