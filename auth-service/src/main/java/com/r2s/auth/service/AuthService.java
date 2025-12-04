package com.r2s.auth.service;

import com.r2s.auth.dto.AuthResponse;
import com.r2s.auth.dto.LoginRequest;
import com.r2s.auth.dto.RegisterRequest;
import com.r2s.auth.entity.Role;
import com.r2s.auth.entity.User;
import com.r2s.auth.repository.UserRepository;
import com.r2s.auth.security.JwtUtil;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
      AuthenticationManager authenticationManager) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.authenticationManager = authenticationManager;
  }

  public void register(RegisterRequest request) {

    if (userRepo.findByUsername(request.getUsername()).isPresent()) {
      throw new RuntimeException("Username exists");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    Role role = request.getRole() == null ? Role.USER : request.getRole();
    user.setRole(role);

    userRepo.save(user);
  }


  public AuthResponse login(LoginRequest request) {
    Authentication authentication;

    try {
      authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(), request.getPassword()
          )
      );
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid email or password"); // 401
    }

    User user = (User) authentication.getPrincipal();

    String roleName = user.getRole().name();

    if (roleName.startsWith("ROLE_")) {
      roleName = roleName.substring(5);
    }

    String token = jwtUtil.generateToken(user.getUsername(), Map.of("role", roleName));

    return new AuthResponse(token);
  }
}