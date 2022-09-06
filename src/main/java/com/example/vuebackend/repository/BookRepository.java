package com.example.vuebackend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Book;
import com.example.vuebackend.repository.dto.BookNameAndCategory;

public interface BookRepository extends JpaRepository<Book,Long>{
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();
    List<Book> findByDeletedIsFalse();
    List<Book> findByCategoryIsNullAndDeletedIsFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = "select b from Book b "
    + "where name = ?1 and createdAt >= ?2 and updatedAt>= ?3 and category is null")
    List<Book> findByNameRecently(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = "select b from Book b "
    + "where name = :name and createdAt >= :createdAt and updatedAt>= :updatedAt and category is null")
    List<Book> findByNameRecently2(
        @Param("name") String name,
        @Param("createdAt") LocalDateTime createdAt, 
        @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select new com.example.vuebackend.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.example.vuebackend.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);

    @Query(value = "select b.name as name, b.category as category from Book b")
    List<Tuple> findBookNameAndCategoryOld();

    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    @Transactional
    @Modifying
    @Query(value = "update book set category = 'IT'", nativeQuery = true)
    int updateCategories();

    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
