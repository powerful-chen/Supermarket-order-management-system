package com.chen.service.role;

import com.chen.dao.BaseDao;
import com.chen.dao.role.RoleDao;
import com.chen.dao.role.RoleDaoImpl;
import com.chen.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/6 12:26
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    //懒加载
    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    //获取角色列表
    @Override
    public List<Role> getRoleList() {

        Connection connection = null;
        List<Role> roleList = null;

        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResources(connection, null, null);
        }
        return roleList;
    }

    @Test
    public void test() {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        System.out.println(roleList.toString());
    }
}
