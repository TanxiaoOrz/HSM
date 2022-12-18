package com.example.hsm.TypeServlet;

import com.example.hsm.beans.Type;
import com.example.hsm.beans.User;
import com.example.hsm.dao.TypeDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ModifyTypeServlet", value = "/ModifyType")
public class ModifyTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);
        if (ServletUtil.checkUserType(request, User.TypeManager)) {
            Type type = new Type();
            type.setTypeName(request.getParameter("TypeName"));
            type.setTypePrice(request.getParameter("TypePrice"));
            type.setTid(Integer.parseInt(request.getParameter("Tid")));

            String str;
            if (TypeDao.modifyType(type)) {
                str = "修改成功";
            } else {
                str = "修改失败";
            }
            String s = new GsonBuilder().create().toJson(str);
            PrintWriter writer = response.getWriter();
            writer.print(s);
        }
    }
}
