package com.example.hsm.CheckPeopleServlet;

import com.example.hsm.dao.CheckPeopleDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCheckPeopleToBookServlet", value = "/AddCheckPeopleToBook")
public class AddCheckPeopleToBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkLogin(request)){
            ServletUtil.setCharsetJSON(request,response);

            Integer Bid = Integer.parseInt(request.getParameter("Bid"));
            Integer Cid = Integer.parseInt(request.getParameter("Cid"));

            String str;
            if (CheckPeopleDao.addCheckPeopleToBook(Bid,Cid)){
                str = "添加成功";
            }else {
                str = "添加失败";
            }
            String s = new GsonBuilder().create().toJson(str);
            PrintWriter writer = response.getWriter();
            writer.println(s);

        }
    }
}
