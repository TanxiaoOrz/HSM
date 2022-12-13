package com.example.hsm.TypeServlet;

import com.example.hsm.beans.Type;
import com.example.hsm.dao.TypeDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetTypeByTidServlet", value = "/GetTypeByTid")
public class GetTypeByTidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        Type type = TypeDao.getTypeByTid(Integer.parseInt(request.getParameter("Tid")));

        String s = new GsonBuilder().create().toJson(type);
        PrintWriter writer = response.getWriter();
        writer.print(s);
    }
}
