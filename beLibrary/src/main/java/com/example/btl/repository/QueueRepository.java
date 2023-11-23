package com.example.btl.repository;

import com.example.btl.entity.QueueBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<QueueBorrow, Long> {
    @Query(value = "SELECT * FROM queue_borrow q WHERE q.book_id = :id", nativeQuery = true)
    QueueBorrow getQueueBorrowByBookId(Long id);


}
