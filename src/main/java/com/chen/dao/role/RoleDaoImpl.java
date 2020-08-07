package com.chen.dao.role;

import com.chen.dao.BaseDao;
import com.chen.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleDaoImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/6 11:56
 */
public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {

        PreparedStatement psvm = null;
        ResultSet rs = null;
        String sql = "select * from smbms_role";
        ArrayList<Role> roleList = new ArrayList<>();

        if (connection != null) {
            connection = BaseDao.getConnection();
            Object[] params = {};
            rs = BaseDao.execute(connection, psvm, rs, sql, params);
            while (rs.next()) {
                Role _role = new Role();
                _role.setId(rs.getInt("id"));
                _role.setRoleCode(rs.getString("roleCode"));
                _role.setRoleName(rs.getString("roleName"));
                roleList.add(_role);
            }
            BaseDao.closeResources(null, psvm, rs);
        }

        return roleList;
    }
}
