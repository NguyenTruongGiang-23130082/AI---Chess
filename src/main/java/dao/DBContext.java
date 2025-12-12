package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    // Hàm này dùng để kết nối với Database của bạn
    public static Connection getConnection() throws Exception {
        // 1. Thay tên database của bạn vào đây (chỗ webbansanphamgotrangtri_db)
        String url = "jdbc:mysql://localhost:3306/webbansanphamgotrangtri_db";
        String user = "root";  // Mặc định của XAMPP
        String password = "";  // Mặc định của XAMPP là rỗng

        // 2. Load Driver MySQL (Đoạn này lấy từ thư viện bạn vừa add vào pom.xml)
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, user, password);
    }

    // Hàm main này để chạy thử xem kết nối được chưa (Test)
    public static void main(String[] args) {
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            if (conn != null) {
                System.out.println("--------------------------------");
                System.out.println("KẾT NỐI THÀNH CÔNG RỒI BẠN ƠI!");
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            System.out.println("--------------------------------");
            System.out.println("LỖI RỒI: " + e.getMessage());
            System.out.println("--------------------------------");
        }
    }
}
