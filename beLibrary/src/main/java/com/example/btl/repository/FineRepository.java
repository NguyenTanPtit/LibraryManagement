package com.example.btl.repository;

import com.example.btl.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Long> {

    @Query(value = "SELECT * FROM fine WHERE user_id = :userId", nativeQuery = true)
    public Fine findByUserId(Long userId);
}
