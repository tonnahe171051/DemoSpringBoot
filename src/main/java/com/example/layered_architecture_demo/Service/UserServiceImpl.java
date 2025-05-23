package com.example.layered_architecture_demo.Service;

import org.springframework.stereotype.Service;
import java.util.Collections;
import com.example.layered_architecture_demo.Model.AppUser;
import com.example.layered_architecture_demo.Repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, Collections.emptyList());
        //khong chap nhan null, phai truyen vao emptyList() de khong bi null
    }
}
