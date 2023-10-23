//package com.example.btl.service;
//
//import com.example.btl.entity.CustomUserDetails;
//import com.example.btl.repository.UserRepository;
//import com.example.btl.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user=  userRepository.findByUsername(username);
//        if (user == null) {
//            System.out.println("User not found!" + username);
//            throw new UsernameNotFoundException("User " + username + " was not found in the database");
//        }
//        return new CustomUserDetails(user.get());
//    }
//}
