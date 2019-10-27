package com.qf.myblog.service;

import com.qf.myblog.pojo.Article;
import org.apache.tools.ant.types.resources.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文章的服务层接口
 */
@Service
public interface ArticleService {
    /**
     * 获取所有文章
     * @return
     */

    int selectSort();

    int selectTitle();

    /**
     * 获取文章的分类
     * @return
     */


    Article selectById(Integer id);

    void star(Integer id);


    List<Article> getVisitAndCount();

    int selectLog();

    Map<String, Integer> getSortAndCount();


    Map<String, List<Article>> getSortAndArticle();


    Article selectpre(int i);

    Article selectnext(int i);


    Map<String, List<Article>> selectArticleBySort(String get);
}
