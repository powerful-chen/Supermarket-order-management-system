package com.chen.service.user;

import com.chen.pojo.User;

import java.util.List;

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

    //根据条件查询用户表记录数
    public int getUserCount(String userName, int userRole);

    //根据条件查询用户列表
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize);

    //添加用户信息
    public boolean add(User user);

    //根据userCode查询出User
    public User selectUserCodeExist(String userCode);

    //通过userId获取user
    public User getUserById(String id);

    //修改用户信息
    public boolean modify(User user);

    //通过ID删除 user
    public boolean deleteUserById(Integer delId);
}
