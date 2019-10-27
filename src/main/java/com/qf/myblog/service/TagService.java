package com.qf.myblog.service;

import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TagService {

    List<Tag> getIdAndTag();

    Map<String, List<Article>> selectTagAndArticle();

    Map<String, List<Article>> selectArticleByTag(String get);
}
