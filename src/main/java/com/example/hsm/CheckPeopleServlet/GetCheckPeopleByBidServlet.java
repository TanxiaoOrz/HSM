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

@WebServlet(name = "GetCheckPeopleByBidServlet", value = "/GetCheckPeopleByBid")
public class GetCheckPeopleByBidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletUtil.checkLogin(request)){
            ServletUtil.setCharsetJSON(request,response);
            int Bid = Integer.parseInt(request.getParameter("Bid"));
            ArrayList<CheckPeople> checkPeopleArrayList = CheckPeopleDao.getCheckPeopleByBid(Bid);

            String s = new GsonBuilder().create().toJson(checkPeopleArrayList);
            PrintWriter writer = response.getWriter();
            writer.println(s);
        }
    }
}
