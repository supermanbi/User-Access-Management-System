package com.r2s.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> userAccess(){
        return ResponseEntity.ok("Hello USER");
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> adminAccess(){
        return ResponseEntity.ok("Hello ADMIN");

    }
    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<String> moderatorAccess(){
        return ResponseEntity.ok("Hello MODERATOR");
    }
}
