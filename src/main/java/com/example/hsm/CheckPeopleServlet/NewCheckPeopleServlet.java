package com.example.hsm.CheckPeopleServlet;

import com.example.hsm.beans.CheckPeople;
import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.dao.CheckPeopleDao;
import com.example.hsm.utils.ServletUtil;
import com.example.hsm.utils.SessionAttribute;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "NewCheckPeopleServlet", value = "/NewCheckPeople")
public class NewCheckPeopleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkLogin(request)){
            ServletUtil.setCharsetJSON(request,response);
            CheckPeople checkPeople = new CheckPeople();
            checkPeople.setCheckPhone(request.getParameter("CheckPhone"));
            checkPeople.setCheckName(request.getParameter("CheckName"));
            checkPeople.setCheckCode(request.getParameter("CheckCode"));
            boolean b;
            if (ServletUtil.checkUserType(request, User.TypeClient)){
                b = CheckPeopleDao.newCheckPeopleWithUid(((User)request.getSession().getAttribute(SessionAttribute.UserAttribute)).getUid(),checkPeople);
            }else {
                b = CheckPeopleDao.newCheckPeople(checkPeople);
            }
            String str;
            if (b){
                str = "新建成功";
            }else {
                str = "新建失败";
            }
            String s = new GsonBuilder().create().toJson(str);
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
