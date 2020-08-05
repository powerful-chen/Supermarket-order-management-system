package com.chen.service.user;

import com.chen.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 15:05
 */
public interface UserService {
    //用户登录
    public User login(String userCode, String password);

    //根据用户id修改密码
    public boolean updatePwd(int id, String password);
}
