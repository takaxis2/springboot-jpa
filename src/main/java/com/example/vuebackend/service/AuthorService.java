package com.example.vuebackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Author;
import com.example.vuebackend.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthor(){
        Author author = new Author();
        author.setName("marrtin");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생했습니다. transaction은 어떻게 될까요?");
    }

}
