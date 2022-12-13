package com.example.hsm.BookServlet;

import com.example.hsm.beans.Book;
import com.example.hsm.beans.User;
import com.example.hsm.dao.BookDao;
import com.example.hsm.utils.ServletUtil;
import com.example.hsm.utils.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "NewBookServlet", value = "/NewBook")
public class NewBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkLogin(request)){
            ServletUtil.setCharsetJSON(request,response);

            Book book = new Book();
            book.setRid(Integer.parseInt(request.getParameter("Rid")));
            book.setStartTime(Date.valueOf(request.getParameter("StartTime")));
            book.setEndTime(Date.valueOf(request.getParameter("EndTime")));

            if (ServletUtil.checkUserType(request,User.TypeClient)){
                book.setUserCode(((User)request.getSession().getAttribute(SessionAttribute.UserAttribute)).getUserCode());
            }else {
                book.setUserCode(request.getParameter("UserCode"));
            }
            String s;
            if (BookDao.newBook(book)){
                s ="新建成功";
            }else{
                s ="新建失败";
            }
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
