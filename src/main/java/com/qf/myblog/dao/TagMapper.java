package com.qf.myblog.dao;

import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Tag;

import java.util.List;

public interface TagMapper {
    int insert(Tag record);



    List<Tag> getIdAndTag();


    List<Tag> selectTagByName(String get);
}