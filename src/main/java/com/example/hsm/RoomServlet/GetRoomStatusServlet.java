package com.example.hsm.RoomServlet;

import com.example.hsm.beans.RoomStatus;
import com.example.hsm.dao.RoomDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetRoomStatusServlet", value = "/GetRoom")
public class GetRoomStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        RoomStatus roomStatus = RoomDao.getRoomStatus();

        String s = new GsonBuilder().create().toJson(roomStatus);
        PrintWriter writer = response.getWriter();
        writer.println(s);
    }
}
