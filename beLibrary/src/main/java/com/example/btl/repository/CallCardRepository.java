package com.example.btl.repository;

import com.example.btl.entity.Book;
import com.example.btl.entity.CallCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallCardRepository extends JpaRepository<CallCard, Long> {
    @Query("select b from Book b join CallCardDetail c on b.id = c.bookId where c.id = :id")
    List<Book> getAllBookByCallCard_Id(Long id);

    @Query("select c from CallCard c where c.user.id = :id")
    List<CallCard> getAllByUserId(Long id);


    //find All user by book id
    @Query("select c from CallCard c join CallCardDetail cd on CAST(c.id AS string) = cd.callCardId where cd.bookId = :id")
    List<CallCard> getAllByBookId(Long id);

    //find call card by borrow date and book id and user id
    @Query("select c from CallCard c join CallCardDetail cd on CAST(c.id AS string) = cd.callCardId where cd.bookId = :bookId and c.borrowDate = :borrowDate and c.user.id = :userId")
    CallCard findByBorrowDateAndBookId(String borrowDate, Long bookId, Long userId);

}
