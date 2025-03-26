package com.staj.liquibasepractice.security.auth;

import com.staj.liquibasepractice.entity.Role;
import com.staj.liquibasepractice.entity.User;
import com.staj.liquibasepractice.dto.request.LogRequest;
import com.staj.liquibasepractice.dto.request.RegisterRequest;
import com.staj.liquibasepractice.dto.response.LogResponse;
import com.staj.liquibasepractice.repository.RoleRepository;
import com.staj.liquibasepractice.repository.UserRepository;
import com.staj.liquibasepractice.security.UserDetailsImpl;
import com.staj.liquibasepractice.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository; //this is needed for register to create new user to save
    private final RoleRepository roleRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LogResponse login(LogRequest logRequest) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  logRequest.email(),
                  logRequest.password()
          )
        );

        User user =userRepository.findByEmail(logRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        String jwtToken = jwtService.generateToken(userDetails);

        return new LogResponse(user.getEmail(),user.getUsername(),user.getRole().getName(), jwtToken);
    }

    public LogResponse register(RegisterRequest registerRequest) {
        Role defaultRole = roleRepository.findById(2L).orElseThrow();

        User user = User.builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(defaultRole)
                .build();
        User savedUser = userRepository.save(user);

        UserDetailsImpl userDetails = new UserDetailsImpl(savedUser);

        String jwtToken = jwtService.generateToken(userDetails);

        return new LogResponse(savedUser.getEmail(),savedUser.getUsername(),savedUser.getRole().getName(), jwtToken);
    }
}
