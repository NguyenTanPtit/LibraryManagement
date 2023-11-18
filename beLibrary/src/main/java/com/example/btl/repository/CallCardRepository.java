package com.example.btl.repository;

import com.example.btl.entity.Book;
import com.example.btl.entity.CallCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallCardRepository extends JpaRepository<CallCard, Long> {
    @Query("select b from Book b join CallCardDetail c on b.id = c.bookId where c.id = :id")
    List<Book> getAllBookByCallCard_Id(Long id);

}
