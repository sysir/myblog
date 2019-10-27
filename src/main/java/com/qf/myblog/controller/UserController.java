package com.qf.myblog.controller;

import com.qf.myblog.pojo.Article;
import com.qf.myblog.pojo.Tag;
import com.qf.myblog.pojo.User;
import com.qf.myblog.service.ArticleService;
import com.qf.myblog.service.TagService;
import com.qf.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * 用户控制层
 */
@Controller
@RequestMapping("/users")
public class UserController {
    /*
    * @Resource
    * 根据名称注入，只要有相关信息就注入范围大
    *@Autowired
    * 根据类型注入
    *都是注解，功能相同
    * */
    @Resource
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    //设置路径访问   通过接口文档
    @RequestMapping("/index")
    public String index(HttpServletRequest request,Model model){
        //先判断session是否有用户 redis数据库中判断token令牌
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            //将session的过期时间刷新
            session.setMaxInactiveInterval(300);
            //需要加载静态的页面信息
            ini(model);
            return "main";//做了视图解析器
        }else{
            return "login";
        }
        //如果有进入主页面，如果没有进入登录界面
    }
    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Model model){

        User user = userService.login(username, password);
        //返回需要的界面login.jsp main.jsp
        if(user!=null){
            //返回main.jsp
            //将登陆成功的user放到session中并设置失效时间
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(300);
            //需要从文章的服务层提供一个函数

            // 携带主页初始化信息返回
            ini(model);
            return "main";
        }else{
            //携带错误信息,效果一样
            //request.setAttribute("msg","用户名或者密码错误");
            model.addAttribute("msg","用户名或者密码错误");
            return "login";
        }
    }
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return "login";
    }

    //封装一个初始化页面信息的函数
    public void ini(Model model){
        List<Article> articleList=articleService.getVisitAndCount();//泛型指定存放article
        model.addAttribute("article_list",articleList);
        //实现文章分类个数 分类个数 标签个数
        //实现日志计数
        int log=articleService.selectLog();
        model.addAttribute("article_number",log);

        //实现分类计数
        int sort = articleService.selectSort();
        model.addAttribute("sort_number",sort);

        //实现标签计数
        int title=articleService.selectTitle();
        model.addAttribute("tag_number",title);

        //实现分类统计sort_count_map key值为分类名称  value为统计次数
        Map<String,Integer> sort_count_map=articleService.getSortAndCount();
        model.addAttribute("sort_count_map",sort_count_map);

        //主页面标签操作
        List<Tag> tagList=tagService.getIdAndTag();
        model.addAttribute("tag_list",tagList);
    }
}
