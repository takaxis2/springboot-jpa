package com.example.vuebackend.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.vuebackend.domain.converter.BookStatusConverter;
import com.example.vuebackend.repository.dto.BookStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Where(clause = "deleted = false") //쿼리 자동 추가
// @EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;


    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> books = new ArrayList<>();

    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name="publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    // @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    private boolean deleted;

    // @Convert(converter = BookStatusConverter.class) 
    /**
     * @Converter의 autoApply속성을 true로 주면 상관 없음
     * 주의 사항, 개발자가 생성한 클래스에 한에서 활용해야함
     * Integer, String, Long 등 general 타입의 클래스에 대해서는 autoApply대신 위에처럼 각각 엔티티의 컬럼에
     * 적용해주는것이 좋다
     */
    private BookStatus status;

    public void addBookAndAuthor(BookAndAuthor... bookAndAuthor){
        Collections.addAll(this.bookAndAuthors, bookAndAuthor);
    }



}
