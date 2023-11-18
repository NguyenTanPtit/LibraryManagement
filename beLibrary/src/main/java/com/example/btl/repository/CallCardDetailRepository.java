package com.example.btl.repository;

import com.example.btl.entity.Book;
import com.example.btl.entity.BookDto;
import com.example.btl.entity.CallCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallCardDetailRepository extends JpaRepository<CallCardDetail, Long> {

    @Query("select b from Book b join CallCardDetail c on b.id = c.bookId where c.callCardId = CAST(:id AS string)")
    List<Book> getAllBookByCallCard_Id(Long id);

    @Query("delete from CallCardDetail c where c.callCardId = :id")
    void deleteAllByCallCardId(Long id);

    @Query("select c from CallCardDetail c where c.callCardId = :id")
    List<CallCardDetail> getAllByCallCardId(Long id);




}
