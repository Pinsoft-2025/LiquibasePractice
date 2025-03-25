package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.entity.Role;
import com.staj.liquibasepractice.entity.User;
import com.staj.liquibasepractice.entity.dto.request.CreateUserRequest;
import com.staj.liquibasepractice.repository.RoleRepository;
import com.staj.liquibasepractice.repository.UserRepository;
import com.staj.liquibasepractice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public User createUser(CreateUserRequest request) {

        //give role USER as default
        Role defaultRole = roleRepository.findById(2L).orElseThrow();

        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(bCryptPasswordEncoder.encode(request.password()));
        user.setRole(defaultRole);
        return userRepository.save(user);
    }

    //crud and extra user stuff
}
