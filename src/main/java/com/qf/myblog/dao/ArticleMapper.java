package com.qf.myblog.dao;

import com.qf.myblog.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);


    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> selectSort();

    List<Article> selectTitle();

    /**
     * 通过文章获取文章的分类及个数
     * @return
     */
    List<Article> selectSortAndCount();

    /**
     * 增加点赞次数
     * @param id
     */
    void updataStar(Integer id);

    List<Article> getVisitAndCount();

    List<Article> selectLog();



    List<Article> selectBySort(String sort);
}