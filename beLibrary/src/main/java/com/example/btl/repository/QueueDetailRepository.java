package com.example.btl.repository;

import com.example.btl.entity.BorrowQueueDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueDetailRepository extends JpaRepository<BorrowQueueDetail, Long>{

    BorrowQueueDetail findByUser_Id(Long id);

    BorrowQueueDetail save(BorrowQueueDetail borrowQueueDetail);

}
