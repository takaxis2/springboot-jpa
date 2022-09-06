package com.example.vuebackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.vuebackend.domain.Book;
import com.example.vuebackend.repository.AuthorRepository;
import com.example.vuebackend.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest(){
        try {
            bookService.putBookAndAuthor();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors :" + authorRepository.findAll());
    }

    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("JPA study");

        bookRepository.save(book);

        bookService.get(1L);

    }

    @Test
    void converterErrorTest(){
        bookService.getAll();
        /**
         * 트랜잭션 또는 세션이 완료되는 시점에 영속성 컨텍스트는 해당 엔티티에
         * 변경된 내역을 확인하고 반영한다
         * 만약 컨버터를 덜 구현한다면, 조회했을때와 디비에 확인하는 값이 다르기때문에 
         * 영속성 컨텍스트는 변경된것으로 감지하고 자동으로 업데이트 쿼리 실행
         * 여기서는 convertToDatabaseColumn()에서 null을 리턴함
         */
        bookRepository.findAll().forEach(System.out::println);
    }

}
