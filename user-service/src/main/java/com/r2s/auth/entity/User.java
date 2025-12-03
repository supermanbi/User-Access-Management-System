package com.r2s.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // <--- QUAN TRỌNG: Sửa tên bảng để hết lỗi "syntax error"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- BỔ SUNG: Để ID tự tăng
    private Long id;

    private String username;
    private String email;
    private String fullName;

    @Enumerated(EnumType.STRING) // <--- BỔ SUNG: Lưu Role dạng chữ (ADMIN) cho an toàn
    private Role role;

    // --- Getter và Setter ---

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
}