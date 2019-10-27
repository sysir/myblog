package com.qf.myblog.service;

import com.qf.myblog.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {


    List<Comment> selectByPrimaryKey(Integer id);

    void stars(Integer id);

    Comment selectById(Integer id);


    void diss(Integer id);

    void deletecm(Integer id);


    void addcom(Comment comment);
}
