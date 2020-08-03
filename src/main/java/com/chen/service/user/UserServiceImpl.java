package com.chen.service.user;

import com.chen.dao.BaseDao;
import com.chen.dao.user.UserDao;
import com.chen.dao.user.UserDaoImpl;
import com.chen.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 19:18
 */
public class UserServiceImpl implements UserService {

    //业务层都会调用dao层，所以我们要用Dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return user;
    }

    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234342342");
        System.out.println(admin.getUserPassword());
    }
}
