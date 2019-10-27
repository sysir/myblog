package com.qf.myblog.service;

import com.qf.myblog.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 用户服务层
 */
@Service
public interface UserService {
    /**
     * 用户登录接口
     * @param username
     * @param password
     */
    User login(String username, String password);
}
