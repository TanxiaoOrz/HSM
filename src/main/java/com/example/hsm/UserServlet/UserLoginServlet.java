package com.example.hsm.UserServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.UserDao;
import com.example.hsm.utils.ServletUtil;
import com.example.hsm.utils.SessionAttribute;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "UserLoginServlet", value = "/UserLogin")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        User user=UserDao.select(request.getParameter("UserCode"),request.getParameter("UserPass"),request.getParameter("UserType"));
        request.getSession().setAttribute(SessionAttribute.UserAttribute,user);
        //将当前用户信息转化为json传回给前端（用的JSON的转化方法）
        String s = new GsonBuilder().create().toJson(user);
        PrintWriter writer = response.getWriter();
        writer.print(s);
    }
}
