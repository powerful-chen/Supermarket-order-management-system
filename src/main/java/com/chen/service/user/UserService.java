package com.chen.service.user;

import com.chen.pojo.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 15:05
 */
public interface UserService {
    //用户登录
    public User login(String userCode, String password);
}