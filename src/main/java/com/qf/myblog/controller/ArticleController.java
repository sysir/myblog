package com.qf.myblog.controller;

import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Comment;
import com.qf.myblog.pojo.Message;
import com.qf.myblog.service.ArticleService;
import com.qf.myblog.service.CommentService;
import com.qf.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 文章控制层
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    //需要一个函数 获取当前文章 返回到共享域中  返回article.jsp
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;


    @RequestMapping("/getById")
    public String article(Integer id, Model model) {
        Article article = articleService.selectById(id);
        model.addAttribute("article", article);

        List<Comment> comment = commentService.selectByPrimaryKey(id);
        model.addAttribute("comment", comment);

        Article article_pre = articleService.selectpre(id - 1);
        Article article_next = articleService.selectnext(id + 1);
        model.addAttribute("article_pre", article_pre);
        model.addAttribute("article_next", article_next);

        return "article";
    }

    @RequestMapping("/getSortInfo")
    public String sort(Model model) {
        //实现分类点击显示分类中的全部文章
        Map<String, List<Article>> sort_article_2 = articleService.getSortAndArticle();
        model.addAttribute("sort_article_map", sort_article_2);
        return "sort";
    }


    @RequestMapping("/getSortAndArticle")
    public String getSortAndArticle(String get, Model model) {
        Map<String, List<Article>> sort_article_1 = articleService.selectArticleBySort(get);
        model.addAttribute("sort_article_map", sort_article_1);


        return "sort";
    }


    //通过异步方式实现点赞
    //通过点击点赞按钮实现发送异步请求
    //接收请求后实现通过文章id确定点赞文章，然后将数据库中将文章点赞次数加1
    //返回一个点赞次数给前端  articles/star?id=12
    //如果是ajax请求  返回在ajax请求对象中
    @RequestMapping("/star")
    @ResponseBody//将数据转换为json格式
    public Article star(Integer id) {
        //全局异常处理
        try {
            articleService.star(id);
            Article article = articleService.selectById(id);
            Message msg = new Message();
            msg.setObject(article);
            msg.setEcode("200");
            return article;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/stars")
    @ResponseBody//将数据转换为json格式
    public Comment stars(Integer id) {
        try {
            commentService.stars(id);
            Comment comment = commentService.selectById(id);
            Message msg = new Message();
            msg.setObject(comment);
            msg.setEcode("200");
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("/diss")
    @ResponseBody
    public Comment diss(Integer id) {
        try {
            commentService.diss(id);
            Comment comment = commentService.selectById(id);
            Message msg = new Message();
            msg.setObject(comment);
            msg.setEcode("200");
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //通过异步实现删除评论功能
    @RequestMapping("/deletecm")
    @ResponseBody
    public Comment comment(Integer id) {
        try {
        commentService.deletecm(id);
        Comment comment = commentService.selectById(id);
        Message msg = new Message();
        msg.setObject(comment);
        msg.setEcode("200");
        return comment;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }

    }

    @RequestMapping("/com")
    @ResponseBody
    public Comment com(Comment comment) {
        try {
            commentService.addcom(comment);

            Message msg = new Message();
            msg.setObject(comment);
            msg.setEcode("200");
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
