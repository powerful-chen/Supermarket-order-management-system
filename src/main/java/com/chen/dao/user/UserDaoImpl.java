package com.chen.dao.user;

import com.chen.dao.BaseDao;
import com.chen.pojo.User;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 14:43
 */
public class UserDaoImpl implements UserDao {
    //得到要登录的用户
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from smbms_user where userCode=?";
            Object[] paramas = {userCode};
            rs = BaseDao.execute(connection, pstm, rs, sql, paramas);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResources(null, pstm, rs);
        }
        return user;
    }

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {

        PreparedStatement pstm = null;
        int execute = 0;
        if (connection != null) {
            Object[] params = {password, id};
            String sql = "update smbms_user set userPassword = ? where id = ?";
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResources(null, pstm, null);
        }
        return execute;
    }

    //通过条件查询-用户表记录数
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;//记录有多少条数据
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?"); //拼接sql
                list.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();//将list 转换为数组
            System.out.println("sql-->" + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResources(null, pstm, rs);
        }
        return count;
    }

    //通过条件查询-userList
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");

            List<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate desc limit ?,?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql --->" + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while (rs.next()) {
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResources(null, pstm, rs);
        }
        return userList;
    }

    //添加用户信息
    @Override
    public int add(Connection connection, User user) throws SQLException {

        PreparedStatement psvm = null;
        int updateRows = 0;
        if (connection != null) {
            String sql = "insert into smbms_user(userCode, userName, userPassword," +
                    "userRole, gender, birthday, phone, address,creationDate,createdBy)" +
                    " values (?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(),
                    user.getUserRole(), user.getGender(), user.getBirthday(), user.getPhone(),
                    user.getAddress(), user.getCreationDate(), user.getCreatedBy()
            };
            updateRows = BaseDao.execute(connection, psvm, sql, params);
            BaseDao.closeResources(null, psvm, null);
        }
        return updateRows;
    }

    //通过userId获取user
    @Override
    public User getUserById(Connection connection, String id) throws SQLException {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (connection != null) {
            String sql = "select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole=r.id and u.id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
                user.setUserRoleName(rs.getString("userRoleName"));
            }
            BaseDao.closeResources(null, pstm, rs);
        }
        return user;
    }

    //修改用户信息
    @Override
    public int modify(Connection connection, User user) throws SQLException {

        int flag = 0;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "update smbms_user set userName=?," +
                    "gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id=?";
            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(),
                    user.getPhone(), user.getAddress(), user.getUserRole(), user.getModifyBy(),
                    user.getModifyDate(), user.getId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResources(null, pstm, null);
        }
        return flag;
    }

    //通过userId删除user
    @Override
    public int deleteUserById(Connection connection, Integer delId) throws SQLException {

        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {

            String sql = "delete from smbms_user where id = ?";
            Object[] params = {delId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResources(null, pstm, null);
        }
        return flag;
    }
}

