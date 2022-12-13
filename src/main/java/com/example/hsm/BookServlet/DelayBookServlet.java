package com.example.hsm.BookServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;

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
        if (ServletUtil.checkUserType(request, User.TypeReception)){
            ServletUtil.setCharsetJSON(request,response);
            String s;
            if (BookDao.delayBook(Integer.parseInt(request.getParameter("Bid")), Date.valueOf(request.getParameter("EndTime") ))){
                s = "延长成功";
            }else {
                s = "延长失败";
            }
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
