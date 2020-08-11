package com.chen.dao.user;

import com.chen.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    //通过条件查询-用户表记录数
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException;

    //通过条件查询-userList
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException;

    //添加用户信息
    public int add(Connection connection, User user) throws SQLException;

    //通过userId获取user
    public User getUserById(Connection connection, String id) throws SQLException;

    //修改用户信息
    public int modify(Connection connection, User user) throws SQLException;

    //通过userId删除user
    public int deleteUserById(Connection connection, Integer delId) throws SQLException;

}
