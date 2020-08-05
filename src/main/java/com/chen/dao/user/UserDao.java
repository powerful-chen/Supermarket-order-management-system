package com.chen.dao.user;

import com.chen.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 14:40
 */
public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    //修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

}
