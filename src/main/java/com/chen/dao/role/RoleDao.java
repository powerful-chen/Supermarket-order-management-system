package com.chen.dao.role;

import com.chen.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/6 11:52
 */

//获取角色列表
public interface RoleDao {
    public List<Role> getRoleList(Connection connection) throws SQLException;
}