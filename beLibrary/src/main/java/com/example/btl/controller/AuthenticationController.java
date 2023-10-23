package com.example.btl.controller;

import com.example.btl.payload.request.AuthenticationRequest;
import com.example.btl.payload.request.RegisterRequest;
import com.example.btl.payload.response.ApiResponse;
import com.example.btl.payload.response.AuthenticationResponse;
import com.example.btl.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticate(
            @RequestParam String username,
            @RequestParam String password
    ) {
        var response = service.authenticate(username,password);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
