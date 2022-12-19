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

@WebServlet(name = "CheckBookServlet", value = "/CheckBook")
public class CheckBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeReception)||ServletUtil.checkUserType(request, User.TypeManager)){
            ServletUtil.setCharsetJSON(request,response);
            String str;
            if (BookDao.checkBook(Integer.parseInt(request.getParameter("Bid")))){
                str = "入住成功";
            }else {
                str = "入住失败";
            }
            //System.out.println(s);
            String s = new GsonBuilder().create().toJson(str);
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
