package com.qf.myblog.service.serviceImpl;

import com.qf.myblog.dao.CommentMapper;
import com.qf.myblog.pojo.Comment;
import com.qf.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> selectByPrimaryKey(Integer id) {
        List<Comment> comments = commentMapper.selectByPrimaryKey(id);
        for (Comment comment : comments) {

        }
        return comments;
    }

    //更新评论点赞
    @Override
    public void stars(Integer id) {
        commentMapper.updateStars(id);
    }


    @Override
    public Comment selectById(Integer id) {
        Comment comment = commentMapper.selectById(id);
        return comment;
    }



    //dislike
    @Override
    public void diss(Integer id) {
        commentMapper.updateDiss(id);
    }

    @Override
    public void deletecm(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addcom(Comment comment) {
        commentMapper.insert(comment);
    }

}
