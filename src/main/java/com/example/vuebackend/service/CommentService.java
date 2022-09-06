package com.example.vuebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Comment;
import com.example.vuebackend.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init(){
        for (int i=0; i< 10; i++){
            Comment comment = new Comment();
            comment.setComment("best "+ i);

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething(){
        List<Comment> comments = commentRepository.findAll();

        for(Comment comment : comments){
            comment.setComment("not good");

            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething(){
        Comment comment = new Comment();
        comment.setComment("what is this?");

        /**
         * 객체를 생성하기만 하면 영속성 관리에 들어가지 않기 때문에 dirty check가 발생하지 않는다
         * 고로 db에 반영되지 않는다
         * .find()등으로 찾아오는 경우에는 영속성 관리하에 있기 때문에 자동으로 db에 반영한다
         */

         commentRepository.save(comment);
    }

}
