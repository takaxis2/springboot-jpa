package com.example.vuebackend.service;



import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Book;
import com.example.vuebackend.repository.AuthorRepository;
import com.example.vuebackend.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    public void put(){
        this.putBookAndAuthor();
        /**
         * 스프링 컨테이너는 빈으로 진입할때, 걸려있는 어노테이션의 처리를 한다.
         * 빈 내부에서 다른 메서드를 호출하면 그 메서드의 @Transactional 어노테이션은 무시가 된다(모든 어노테이션이 무시가 되는지 확인 필요)
         * 어노테이션을 읽지 않고 효과가 없다
         */
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    void putBookAndAuthor(){
        Book book = new Book();
        book.setName("Jpa starter");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
            //TODO: handle exception
        }


        /**
         * @Transactional로 db반영을 putBookAndAuthor가 성공적으로 끝났을때 한다
         * 없으면 save할때마다 반영
         */

        //  throw new RuntimeException("오류가 나서 db commit이 발생하지 않았습니다");

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id){
        System.out.println(">>>  " + bookRepository.findById(id));
        System.out.println(">>>  " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>>  " + bookRepository.findById(id));
        System.out.println(">>>  " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();
    }

    @Transactional
    public List<Book> getAll(){
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }
    
}
