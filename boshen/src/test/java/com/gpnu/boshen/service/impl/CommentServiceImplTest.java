package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Test
    void addComment() {
        Comment comment = new Comment();
        comment.setAuthorId(1);
        comment.setContent("testFFFFF");
        comment.setParentCommentId(2);
        System.out.println(commentService.addComment(comment));
    }

    @Test
    void deleteComment() {
        System.out.println(commentService.deleteComment(3));
    }

    @Test
    void findById() {
        Comment comment = new Comment();
        comment.setParentCommentId(2);
        System.out.println(commentService.findById(comment));
    }
}