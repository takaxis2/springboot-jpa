package com.example.vuebackend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Book;
import com.example.vuebackend.domain.Gender;
import com.example.vuebackend.domain.Publisher;
import com.example.vuebackend.domain.Review;
import com.example.vuebackend.domain.User;
import com.example.vuebackend.repository.dto.BookNameAndCategory;
import com.example.vuebackend.repository.dto.BookStatus;

@SpringBootTest
@Transactional
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void bookRelationTest(){

        givenBookAndReview();

        User user = userRepository.findByEmail("david@gmail.com");

        userRepository.findAll().forEach(System.out::println);

        // reviewRepository.findAll().forEach(System.out::println);

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

    }

    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA package");

        Publisher publisher = new Publisher();
        publisher.setName("fastCampus");

        book.setPublisher(publisher);
        bookRepository.save(book);

        // publisher.getBooks().add(book);
        // publisher.addBook(book);
        // publisherRepository.save(publisher);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("slowCampus");

        bookRepository.save(book1);

        System.out.println("publishers : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
        // bookRepository.delete(book2);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);



        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("book3-publisher : " + bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void bookRemoveCascadeTest(){
        // bookRepository.deleteById(1L);
        
        bookRepository.findAll().forEach(System.out::println);
        publisherRepository.findAll().forEach(System.out::println);

        bookRepository.findAll().forEach(book->System.out.println(book.getPublisher()));

        
    }
    
    @Test
    void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
    }

    @Test
    void queryTest(){
        // System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : " +
        //     bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
        //         "JPA package",
        //         LocalDateTime.now().minusDays(1L),
        //         LocalDateTime.now().minusDays(1L)
        //     ));

        // System.out.println("findByRecently : " + bookRepository.findByNameRecently( "JPA package",
        //     LocalDateTime.now().minusDays(1L),
        //     LocalDateTime.now().minusDays(1L)));

        // bookRepository.findBookNameAndCategoryOld().forEach(tuple->{System.out.println(tuple.get(0) + " : " + tuple.get(1));});
        bookRepository.findBookNameAndCategory().forEach(System.out::println);
        bookRepository.findBookNameAndCategory(PageRequest.of(1, 1)).forEach(
            bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory())
        );
    }

    @Test
    void nativeQueryTest(){
        // bookRepository.findAll().forEach(System.out::println);
        // bookRepository.findAllCustom().forEach(System.out::println);

        List<Book> books = bookRepository.findAll();
        
        for(Book book : books){
            book.setCategory("IT");
        }

        bookRepository.saveAll(books);

        bookRepository.findAll().forEach(System.out::println);

        System.out.println("affectedrows : " + bookRepository.updateCategories());
        System.out.println(bookRepository.findAllCustom());

        bookRepository.showTables().forEach(System.out::println);

    }

    @Test
    void converterTest(){
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("another IT book");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
        bookRepository.findAll().forEach(System.out::println);

    }

    private void givenBookAndReview(){
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("ssibal");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("spring, jpa");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User givenUser(){

        User user = new User();
        user.setName("David");
        user.setEmail("david@gmail.com");
        user.setGender(Gender.MALE);


        // return userRepository.save(user);
        return userRepository.findByEmail("david@gmail.com");
    }

    private void givenReview(User user, Book book){
        Review review = new Review();

        review.setTitle("life book");
        review.setContent("so interesting");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

}
