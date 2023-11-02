package com.example.btl.service;


import com.example.btl.entity.ERole;
import com.example.btl.entity.User;
import com.example.btl.payload.request.RegisterRequest;
import com.example.btl.payload.response.ApiResponse;
import com.example.btl.payload.response.AuthenticationResponse;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())
                .role(ERole.STUDENT)
                .build();
        var savedUser = userRepository.save(user);
        return AuthenticationResponse.builder()
                .message("thanh cong")
            .build();
    }

    public ApiResponse authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(user)
                    .httpStatus(HttpStatus.OK)
                    .build();
            return apiResponse;
        }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .data(null)
                .message("tai khoan khong ton tai")
                .build();
    }
}
