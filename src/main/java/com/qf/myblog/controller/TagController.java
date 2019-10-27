package com.qf.myblog.controller;

import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Tag;
import com.qf.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;
@RequestMapping("/getTagByName")
  public String selectTagByName(String get,Model model){
    Map<String,List<Article>> tag_map=tagService.selectArticleByTag(get);
      model.addAttribute("tag_map",tag_map);

      return "tags";
  }
    @RequestMapping("/getTagInfo")
    public String getTagInfo(Model model){
        Map<String,List<Article>> tag_map=tagService.selectTagAndArticle();
        model.addAttribute("tag_map",tag_map);
        return "tags";
    }



}
