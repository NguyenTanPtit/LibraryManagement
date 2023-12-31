package com.example.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.btl.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Integer id);

    @Query("select u from User u where u.role = 'STUDENT'")
    List<User> getAllStudent();
}
