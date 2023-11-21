package com.example.btl.controller;

import com.example.btl.entity.User;
import com.example.btl.entity.UserDTO;
import com.example.btl.payload.response.ApiResponse;
import com.example.btl.payload.response.CreateBookResponse;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value="/api/student")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllStudent() {
        try {
            List<User> data = service.getAllStudent();
            List<UserDTO> result = new LinkedList<>();
            data.forEach(user -> result.add(UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .avatar(user.getAvatar())
                    .password(user.getPassword())
                    .fullName(user.getFullName())
                    .address(user.getAddress())
                    .dateOfBirth(user.getDateOfBirth())
                    .phoneNumber(user.getPhoneNumber())
                    .role(String.valueOf(user.getRole()))
                    .build()));
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<EntityResponse> updateStudent(@RequestBody User user) {
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
            service.update(user);
            EntityResponse data = EntityResponse.builder()
                    .message("Update student successfully!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<EntityResponse> deleteStudent(@RequestParam Long id) {
        try {
            service.delete(id);
            EntityResponse response =  EntityResponse.builder()
                    .message("Delete student successfully!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            EntityResponse response =  EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EntityResponse> addStudent(@RequestBody User user) {
        try {
            service.add(user);
            EntityResponse data = EntityResponse.builder()
                    .message("Add student successfully!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
            return new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
        }
    }
}
