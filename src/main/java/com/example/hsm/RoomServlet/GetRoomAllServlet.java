package com.example.hsm.RoomServlet;

import com.example.hsm.beans.Room;
import com.example.hsm.dao.RoomDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetRoomAllServlet", value = "/GetRoomAll")
public class GetRoomAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        ArrayList<Room> rooms = RoomDao.getRoomAll();
        String s = new GsonBuilder().create().toJson(rooms);
        PrintWriter writer = response.getWriter();
        writer.print(s);
    }
}
