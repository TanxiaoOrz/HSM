package com.example.hsm.BookServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "DelayBookServlet", value = "/DelayBook")
public class DelayBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeReception)||ServletUtil.checkUserType(request, User.TypeManager)){
            ServletUtil.setCharsetJSON(request,response);
            String str;
            if (BookDao.delayBook(Integer.parseInt(request.getParameter("Bid")), Date.valueOf(request.getParameter("EndTime") ))){
                str = "延长成功";
            }else {
                str = "延长失败";
            }
            String s = new GsonBuilder().create().toJson(str);
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
