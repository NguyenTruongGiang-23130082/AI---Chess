package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    /**
     * Kiểm tra thông tin đăng nhập trong DB.
     * @param email Email người dùng
     * @param password Mật khẩu người dùng
     * @return Đối tượng User nếu đăng nhập thành công, ngược lại trả về null.
     */
    public User checkLogin(String email, String password) {
        // Lưu ý: Tên cột full_name trong DB được map vào thuộc tính username trong Java
        String SQL = "SELECT id, full_name, email, role, status FROM users WHERE email = ? AND password = ?";
        User user = null;
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("full_name")); // full_name từ DB
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Kiểm tra xem một email đã tồn tại trong DB chưa.
     * @param email Email cần kiểm tra
     * @return true nếu tồn tại, false nếu chưa.
     */
    public boolean checkEmailExist(String email) {
        String SQL = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Thực hiện đăng ký tài khoản mới.
     * Mặc định set role='User' và status='Active'.
     */
    public void signup(String username, String email, String password) {
        String SQL = "INSERT INTO users (full_name, email, password, role, status) VALUES (?, ?, ?, 'User', 'Active')";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, username); // full_name
            ps.setString(2, email);    // email
            ps.setString(3, password); // password

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}