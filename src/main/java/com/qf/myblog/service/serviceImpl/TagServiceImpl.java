package com.qf.myblog.service.serviceImpl;

import com.qf.myblog.dao.ArticleMapper;
import com.qf.myblog.dao.TagMapper;
import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Tag;
import com.qf.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Tag> getIdAndTag() {
        List<Tag> tagList=tagMapper.getIdAndTag();
        for (Tag tag : tagList) {
        }
       return tagList;
    }



    @Override
    public Map<String, List<Article>> selectTagAndArticle() {
        List<Article> articleList= articleMapper.selectAll();
        List<Tag> tagList=tagMapper.getIdAndTag();
        List<Article> list=new ArrayList<>();
        Map<String,List<Article>> maps=new HashMap<>();
        for (Tag tag : tagList) {
            for (Article article : articleList) {
                if (tag.getId().equals(article.getId() )){
                    list.add(article);
                }
            }
            maps.put(tag.getTag(),list);
            list=new ArrayList<>();

        }

        return maps;
    }

    @Override
    public Map<String, List<Article>> selectArticleByTag(String get) {
        List<Article> articleList=articleMapper.selectAll();
        List<Tag> tagList=tagMapper.selectTagByName(get);
        List<Article> list=new ArrayList<>();
        Map<String,List<Article>> maps=new HashMap<>();
        for (Tag tag : tagList) {
            for (Article article : articleList) {
                if (tag.getId().equals(article.getId() )) {
                    list.add(article);
                }
            }
            maps.put(tag.getTag(),list);
            list=new ArrayList<>();
        }
        return maps;
    }


}
