package com.chen.service.user;

import com.chen.dao.BaseDao;
import com.chen.dao.user.UserDao;
import com.chen.dao.user.UserDaoImpl;
import com.chen.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public boolean updatePwd(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            userDao.updatePwd(connection, id, password);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return flag;
    }

    //根据条件查询用户表记录数
    @Override
    public int getUserCount(String userName, int userRole) {

        Connection connection = null;
        int count = 0;
        System.out.println("userName-->" + userName);
        System.out.println("userRole-->" + userRole);
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, userName, userRole);//得到用户记录数
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return count;
    }

    //根据条件查询用户列表
    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {

        Connection connection = null;
        List<User> userList = null;
        System.out.println("userName --->" + userName);
        System.out.println("userRole --->" + userRole);
        System.out.println("currentPageNo --->" + currentPageNo);
        System.out.println("pageSize --->" + pageSize);

        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, userName, userRole, currentPageNo, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return userList;
    }

    @Test
    public void test(){
        UserService userService = new UserServiceImpl();
        //int userCount = userService.getUserCount(null, 0);
        //System.out.println(userCount);

        List<User> userList = userService.getUserList("张", 0, 1, 5);
        System.out.println(userList.toString());
    }
}
