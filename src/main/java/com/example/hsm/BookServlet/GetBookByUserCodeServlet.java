package com.example.hsm.BookServlet;

import com.example.hsm.beans.Book;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetBookByUserCodeServlet", value = "/GetBookByUserCode")
public class GetBookByUserCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (ServletUtil.checkLogin(request)){
           ServletUtil.setCharsetJSON(request,response);

           ArrayList<Book> books = BookDao.getBookByUserCode(request.getParameter("UserCode"));
           String s = new GsonBuilder().create().toJson(books);
           PrintWriter writer = response.getWriter();
           writer.println(s);
       }
    }
}
