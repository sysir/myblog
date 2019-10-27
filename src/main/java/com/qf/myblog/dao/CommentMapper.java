package com.qf.myblog.dao;

import com.qf.myblog.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int updateByPrimaryKey(Comment record);

    Comment selectAll();

    List<Comment> selectByPrimaryKey(Integer id);


    void updateStars(Integer id);

    Comment selectById(Integer id);

    void updateDiss(Integer id);


}