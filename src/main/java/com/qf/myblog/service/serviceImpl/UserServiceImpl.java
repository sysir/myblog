package com.qf.myblog.service.serviceImpl;

import com.qf.myblog.dao.UserMapper;
import com.qf.myblog.pojo.User;
import com.qf.myblog.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

/*
@Transactional
自动添加事务
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        //通过用户名查找对应用户
       User user = userMapper.selectByName(username);
       /*
       * 用户不为空，则进行密码对比
       * 如果用户为空，则不许需要进行密码对比操作
       * */
       if(user!=null) {
           //首先对前端传过来的密码进行加密后对比
           Md5Hash md5Hash = new Md5Hash(password, username, 100);
           //md5Hash.toString();
           if (user.getUserPassword().equals(md5Hash.toString())) {
               return user;
           }
       }
        return null;
    }
}
