package com.example.hsm.BookServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckBookServlet", value = "/CheckBook")
public class CheckBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeReception)){
            String s;
            if (BookDao.checkBook(Integer.parseInt(request.getParameter("Bid")))){
                s = "入住成功";
            }else {
                s = "入住失败";
            }
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
