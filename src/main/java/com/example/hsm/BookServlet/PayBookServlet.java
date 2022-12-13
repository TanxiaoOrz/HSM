package com.example.hsm.BookServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PayBookServlet", value = "/PayBook")
public class PayBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeReception)){
            String s;
            if (BookDao.payBook(Integer.parseInt(request.getParameter("Bid")))){
                s = "付款成功";
            }else {
                s = "付款失败";
            }
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
