package com.example.btl.repository;

import com.example.btl.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Long> {

    @Query(value = "SELECT * FROM fine WHERE user_id = :userId", nativeQuery = true)
    public Fine findByUserId(Long userId);

    //increase missing borrow
    @Query(value = "UPDATE fine SET missing_borrow = missing_borrow + 1 WHERE user_id = :userId", nativeQuery = true)
    public void increaseMissingBorrow(Long userId);
}
