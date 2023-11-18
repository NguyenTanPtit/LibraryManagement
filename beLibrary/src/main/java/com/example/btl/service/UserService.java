package com.example.btl.service;

import com.example.btl.entity.User;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //method update user
    public void update(User user) {
        userRepository.save(user);
    }

    //methode delete user
    public void delete(Long id) {
        userRepository.deleteById(id.intValue());
    }

    //method add user
    public User add(User user) {
        return userRepository.save(user);
    }

    //method all student
    public List<User> getAllStudent() {
        return userRepository.getAllStudent();
    }


}
