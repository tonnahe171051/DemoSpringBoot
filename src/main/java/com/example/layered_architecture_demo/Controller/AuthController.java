package com.example.layered_architecture_demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody; //phai goi tu springframework

import com.example.layered_architecture_demo.Model.AppUser;
import com.example.layered_architecture_demo.Repository.UserRepository;
import com.example.layered_architecture_demo.Security.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API endpoints for authentication")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager am, JwtUtils ju,
            UserRepository ur, PasswordEncoder pe) {
        this.authManager = am;
        this.jwtUtils = ju;
        this.userRepo = ur;
        this.encoder = pe;
    }
    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignupRequest req) {
        if (userRepo.existsByUsername(req.username())) {
            return ResponseEntity.badRequest().body("Username đã tồn tại");
        }
        AppUser u = new AppUser();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));
        u.setEmail(req.email());
        if (userRepo.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body("Email đã tồn tại");
        }
        userRepo.save(u);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        String token = jwtUtils.generateToken(auth);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    public static record SignupRequest(String username, String password, String email) {
    }

    public static record LoginRequest(String username, String password) {
    }

    public static record JwtResponse(String token) {
    }
}
