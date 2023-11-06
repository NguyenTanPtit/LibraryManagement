package com.example.btl.repository;

import com.example.btl.entity.Book;
import com.example.btl.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from book where category_id = :id", nativeQuery = true)
    List<Book> getAllByCategories_Id(@Param("id") Long id);
    @Query(value = "select * from book where author_id = :id", nativeQuery = true)
    List<Book> getAllByAuthorId(Long id);

    void deleteById(Integer id);
}
