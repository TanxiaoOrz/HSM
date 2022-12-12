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

@WebServlet(name = "GetRoomServlet", value = "/GetRoomServlet")
public class GetRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);

        Room room = RoomDao.getRoom(Integer.parseInt(request.getParameter("Rid")));

        PrintWriter writer = response.getWriter();
        String s = new GsonBuilder().create().toJson(room);
        writer.print(s);
    }
}
