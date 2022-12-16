package com.example.hsm.UserServlet;

import com.example.hsm.beans.User;
import com.example.hsm.dao.UserDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetReceptionServlet", value = "/GetReception")
public class GetReceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeManager)) {
            ServletUtil.setCharsetJSON(request, response);
            ArrayList<User> receptions = UserDao.getReception();
            String s = new GsonBuilder().create().toJson(receptions);
            PrintWriter writer = response.getWriter();
            writer.print(s);
        }
    }
}
