package com.example.vuebackend.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Author;
import com.example.vuebackend.domain.Book;
import com.example.vuebackend.domain.BookAndAuthor;

@SpringBootTest
@Transactional
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest(){
        Book book1 = givenBook("book1");
        Book book2 = givenBook("book2");
        Book book3 = givenBook("develop1");
        Book book4 = givenBook("develop2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steve");

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);

        // book1.addAuthor(author1);
        // book2.addAuthor(author2);
        // book3.addAuthor(author1, author2);
        // book4.addAuthor(author1, author2);

        // author1.addBook(book1, book3, book4);
        // author2.addBook(book2, book3, book4);

        book1.addBookAndAuthor(bookAndAuthor1);
        book2.addBookAndAuthor(bookAndAuthor2);
        book3.addBookAndAuthor(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthor(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthor(bookAndAuthor1,bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthor(bookAndAuthor2,bookAndAuthor4, bookAndAuthor6);

        
        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        // System.out.println("Authors through book : " + bookRepository.findAll().get(2).getAuthors());
        // System.out.println("Books through author : " + authorRepository.findAll().get(0).getBooks());

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o->System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o->System.out.println(o.getBook()));
    }

    private Book givenBook(String name){
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name){
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author){
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setAuthor(author);
        bookAndAuthor.setBook(book);

       return bookAndAuthorRepository.save(bookAndAuthor);
    }


}
