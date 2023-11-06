package com.example.btl.service;

import com.example.btl.entity.BorrowQueueDetail;
import com.example.btl.entity.QueueBorrow;
import com.example.btl.entity.User;
import com.example.btl.payload.request.QueueRequest;
import com.example.btl.repository.QueueDetailRepository;
import com.example.btl.repository.QueueRepository;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueueService {
     private final QueueDetailRepository queueDetailRepository;
     private final QueueRepository queueRepository;
     private final UserRepository userRepository;

     public void addQueue(QueueBorrow queueRequest) {
          queueRepository.save(queueRequest);
     }

        public void deleteQueue(Long id) {
            queueRepository.deleteById(id);
        }
        public QueueBorrow checkQueueExistByBookId(Long id) {
           return queueRepository.getQueueBorrowByBookId(id);
        }

        @Query(value = "SELECT * FROM borrow_queue_detail q WHERE q.user_id = :id", nativeQuery = true)
        public BorrowQueueDetail checkQueueExistByUserId(Long usid) {
            return queueDetailRepository.findByUser_Id(usid);
        }

        public void addQueueDetail(BorrowQueueDetail queueRequest) {
            queueDetailRepository.save(queueRequest);
        }
        public User getUserById(Long id) {
            Optional<User> user = userRepository.findById(id.intValue());
            return user.get();
        }
}
