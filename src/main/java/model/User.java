package model;

public class User {
    private int id;
    private String username;
    private String password;
    private String status;
    private String role;
    private String email;

    // Constructor đầy đủ
    public User(String username, String password, String status, String role, String email, int id) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.email = email;
        this.id = id;
    }

    // Constructor rút gọn
    public User(int id, String username, String status, String password) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.password = password;
    }

    // Constructor rỗng
    public User() {
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}