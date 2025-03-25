package com.staj.liquibasepractice.security.auth;

import com.staj.liquibasepractice.dto.request.LogRequest;
import com.staj.liquibasepractice.dto.request.RegisterRequest;
import com.staj.liquibasepractice.dto.response.LogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LogResponse> login(@RequestBody LogRequest logRequest) {
        return new ResponseEntity<>(authService.login(logRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<LogResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.OK);
    }
}
