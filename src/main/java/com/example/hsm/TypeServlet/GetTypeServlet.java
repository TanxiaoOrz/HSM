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
import java.util.ArrayList;

@WebServlet(name = "GetTypeServlet", value = "/GetType")
public class GetTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        ArrayList<Type> types = TypeDao.getType();

        String s = new GsonBuilder().create().toJson(types);
        PrintWriter writer = response.getWriter();
        writer.print(s);
    }
}
