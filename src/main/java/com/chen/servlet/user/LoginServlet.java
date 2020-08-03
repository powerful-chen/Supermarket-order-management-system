package com.chen.servlet.user;

import com.chen.pojo.User;
import com.chen.service.user.UserServiceImpl;
import com.chen.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author xiaochen
 * @Date 2020/8/3 19:51
 */
public class LoginServlet extends HttpServlet {
    //Servlet:控制层，调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start---");

        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库中密码进行对比，调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        if (user != null) {
            //将用户信息放到Session中
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //跳转到主页
            resp.sendRedirect("jsp/frame.jsp");
        } else {
            //转发回登录页面，顺带提示它，用户名或者密码错误
            req.setAttribute("error", "用户名或者密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
