package com.qf.myblog.service.serviceImpl;

import com.qf.myblog.dao.ArticleMapper;
import com.qf.myblog.pojo.Article;
import com.qf.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public int selectLog() {
        List<Article> logList = articleMapper.selectLog();
        int size = logList.size();
        return size;
    }

    /**
     * 分类中获取文章
     * 显示分类统计信息
     * @return
     */
    @Override
    public Map<String, List<Article>> getSortAndArticle() {
       List<Article> articleList=articleMapper.selectAll();
       List<Article> sortList=articleMapper.selectSort();
        List<Article> list=new ArrayList<>();//存放map list中的文章
        Map<String,List<Article>> maps=new HashMap<>();
        for (Article article : sortList) {
            for (Article article1 : articleList) {
                if (article.getSort().equals(article1.getSort())){
                    list.add(article1);
                }
            }
            maps.put(article.getSort(),list);
            list=new ArrayList<>();
        }
        return maps;
    }

    @Override
    public Article selectpre(int i) {
        Article article=articleMapper.selectByPrimaryKey(i);
        return article;
    }

    @Override
    public Article selectnext(int i) {
        Article article=articleMapper.selectByPrimaryKey(i);
        return article;
    }

    /**
     * 通过侧边分类  查询各自分类中得文章
     * @param get
     * @return
     */
    @Override
    public Map<String, List<Article>> selectArticleBySort(String get) {
        List<Article> articleList=articleMapper.selectAll();
        List<Article> sortList=articleMapper.selectBySort(get);
        List<Article> list=new ArrayList<>();
        Map<String,List<Article>> maps=new HashMap<>();
        for (Article article : sortList) {
            for (Article article1 : articleList) {
                if (article.getSort().equals(article1.getSort())){
                    list.add(article1);
                }
            }
            maps.put(article.getSort(),list);
            list=new ArrayList<>();
        }
        return maps;
    }

    @Override
    public int selectSort() {
        List<Article> sortList = articleMapper.selectSort();
        int size = sortList.size();
        return size;

    }

    @Override
    public int selectTitle() {
        List<Article> articleList = articleMapper.selectTitle();
        int size=articleList.size();
        return size;
    }
    @Override
    public Map<String, Integer> getSortAndCount() {
        //在article类中添加一个分类数量的属性   通过实体类返回到service中
        //遍历list属性，将需要的数据添加到对应的map集合中
        List<Article> articleList=articleMapper.selectSortAndCount();
        Map<String,Integer> maps=new HashMap<>();
        for (Article article : articleList) {
            maps.put(article.getSort(),article.getCountsort());
        }
        return maps;
    }



    @Override
    public Article selectById(Integer id) {
        Article article=articleMapper.selectByPrimaryKey(id);

        return article;
    }

    @Transactional
    @Override
    public void star(Integer id){
        articleMapper.updataStar(id);
    }

    @Override
    public List<Article> getVisitAndCount() {
        List<Article> articleList = articleMapper.getVisitAndCount();
        for (Article article : articleList) {
            //判断文章内容的长度
            if (article.getContent().length() > 30) {
//                String content = article.getContent();
//                //剪切字符串 substring取前不取后
//                String substring = content.substring(0, 30)+"...";
                article.setContent(article.getContent().substring(0, 200) + "...");

            }
        }
        return articleList;
    }




}
