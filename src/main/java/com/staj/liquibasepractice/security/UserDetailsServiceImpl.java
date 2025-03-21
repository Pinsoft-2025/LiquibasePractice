package com.staj.liquibasepractice.security;

import com.staj.liquibasepractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    //Belirleyici olarak biz Username yerine email kullaniyoruz
    //Username ise oldukca degisken bir deger
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findByEmail(email)
                .map(UserDetailsImpl::new) // Optional<User> → Optional<UserDetailsImpl>
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with the email: " + email));
    }
}
