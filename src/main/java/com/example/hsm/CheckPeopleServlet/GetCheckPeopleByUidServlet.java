package com.example.hsm.CheckPeopleServlet;

import com.example.hsm.beans.CheckPeople;
import com.example.hsm.beans.User;
import com.example.hsm.dao.CheckPeopleDao;
import com.example.hsm.utils.ServletUtil;
import com.example.hsm.utils.SessionAttribute;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetCheckPeopleByUidServlet", value = "/GetCheckPeopleByUid")
public class GetCheckPeopleByUidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkLogin(request)){
            ServletUtil.setCharsetJSON(request,response);
            int Uid;
            if (ServletUtil.checkUserType(request, User.TypeClient)){
                Uid=((User)request.getSession().getAttribute(SessionAttribute.UserAttribute)).getUid();
            }else {
                Uid = Integer.parseInt(request.getParameter("Uid"));
            }
            ArrayList<CheckPeople> checkPeopleArrayList = CheckPeopleDao.getCheckPeopleByUid(Uid);

            String s = new GsonBuilder().create().toJson(checkPeopleArrayList);
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
