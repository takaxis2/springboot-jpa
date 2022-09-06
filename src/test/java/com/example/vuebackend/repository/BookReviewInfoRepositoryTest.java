package com.example.vuebackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Book;
import com.example.vuebackend.domain.BookReviewInfo;

@SpringBootTest
@Transactional
public class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    @Test
    void crudTest2(){

       givenBookReviewInfo();

       System.out.println(">>>>>>>>>>");
       bookReviewInfoRepository.findAll().forEach(System.out::println);

        Book result = bookReviewInfoRepository
            .findById(2L)   
            .orElseThrow(RuntimeException::new)
            .getBook();

        System.out.println(">>> "+ result);

        BookReviewInfo result2 = bookRepository
        .findById(2L)
        .orElseThrow(RuntimeException::new)
        .getBookReviewInfo();

        System.out.println(">>> " + result2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("spring jpa");
        book.setAuthorId(1L);
        // book.setPublisherId(1L);
        System.out.println(">>> " + bookRepository.findAll());
        
        return bookRepository.save(book);
        

    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        
        bookReviewInfoRepository.save(bookReviewInfo);
        
        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

}
