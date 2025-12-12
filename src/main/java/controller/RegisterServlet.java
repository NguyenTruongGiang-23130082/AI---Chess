package controller;

import dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy dữ liệu từ form đăng ký (Tiếng Việt có dấu)
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");

        UserDao dao = new UserDao();

        // 2. Validate dữ liệu
        if (!password.equals(rePassword)) {
            request.setAttribute("MESS_REGISTER", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (dao.checkEmailExist(email)) {
            request.setAttribute("MESS_REGISTER", "Email này đã được sử dụng!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 3. Nếu OK thì gọi hàm signup
        dao.signup(username, email, password);

        // 4. Thông báo thành công và chuyển về trang login
        request.setAttribute("MESS_SUCCESS", "Đăng ký thành công! Vui lòng đăng nhập.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}