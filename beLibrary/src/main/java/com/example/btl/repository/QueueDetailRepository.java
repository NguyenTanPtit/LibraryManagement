package com.example.btl.repository;

import com.example.btl.entity.BorrowQueueDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QueueDetailRepository extends JpaRepository<BorrowQueueDetail, Long>{

    BorrowQueueDetail findByUser_Id(Long id);

    BorrowQueueDetail save(BorrowQueueDetail borrowQueueDetail);

    //find by overdue date
    @Query(value = "SELECT * FROM borrow_queue_detail b WHERE b.date_borrow = :overdueDate", nativeQuery = true)
    List<BorrowQueueDetail> findByOverdueDate(String overdueDate);

    //increase position
    @Query(value = "UPDATE borrow_queue_detail b SET b.position = b.position + 1 WHERE b.queue_borrow_id = :id", nativeQuery = true)
    void increasePosition(Integer id);

    //find by queue borrow id
    @Query(value = "SELECT * FROM borrow_queue_detail b WHERE b.queue_borrow_id = :id", nativeQuery = true)
    List<BorrowQueueDetail> findByQueueBorrowId(Integer id);
}
