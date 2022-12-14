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

@WebServlet(name = "DeleteReceptionServlet", value = "/DeleteReception")
public class DeleteReceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkUserType(request, User.TypeManager)) {
            ServletUtil.setCharsetJSON(request, response);
            PrintWriter writer = response.getWriter();
            String str;
            if (UserDao.delete(request.getParameter("UserCode"),User.TypeReception)){
                str="删除成功";
            }else {
                str="删除失败";
            }
            String s = new GsonBuilder().create().toJson(str);
            writer.println(s);

        }
    }
}
