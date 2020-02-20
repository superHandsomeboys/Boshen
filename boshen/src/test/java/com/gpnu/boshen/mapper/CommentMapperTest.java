package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;
    //先添加一个评论，
    @Test
    void addComment() {
        Comment comment = new Comment();
        comment.setAuthorId(1);
        comment.setContent("testFFFFF");
        comment.setCreateTime(new Date());
        comment.setParentCommentId(1);
        commentMapper.addComment(comment);
    }

    @Test
    void deletComments() {
        commentMapper.deletComment(2);
    }

    @Test
    void findById() {
        Comment comment = new Comment();
//        comment.setAuthorId(1);
        comment.setParentCommentId(1);
        System.out.println(commentMapper.findById(comment));
    }
}