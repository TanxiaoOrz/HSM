package com.example.hsm.RoomServlet;

import com.example.hsm.beans.Room;
import com.example.hsm.beans.Type;
import com.example.hsm.beans.User;
import com.example.hsm.dao.RoomDao;
import com.example.hsm.dao.TypeDao;
import com.example.hsm.utils.ServletUtil;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ModifyRoomServlet", value = "/ModifyRoom")
public class ModifyRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletUtil.setCharsetJSON(request,response);
//        if (ServletUtil.checkUserType(request, User.TypeManager)) {
//            Type type = new Type();
//            type.setTid(Integer.parseInt(request.getParameter("Tid")));
//
//            Room room = new Room();
//            room.setType(type);
//            room.setOrientation(new String(request.getParameter("Orientation").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            room.setFeature(new String(request.getParameter("Feature").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            room.setFloor(new String(request.getParameter("Floor").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            room.setRid(Integer.parseInt(request.getParameter("Rid")));
//
//            String s;
//            if (RoomDao.modifyRoom(room)) {
//                s = "修改成功";
//            } else {
//                s = "修改失败";
//            }
//
//            PrintWriter writer = response.getWriter();
//            writer.print(s);
//        }
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtil.setCharsetJSON(request,response);
        if (ServletUtil.checkUserType(request, User.TypeManager)) {
            Type type = new Type();
            type.setTid(Integer.parseInt(request.getParameter("Tid")));

            Room room = new Room();
            room.setType(type);
            room.setOrientation(request.getParameter("Orientation"));
            room.setFeature(request.getParameter("Feature"));
            room.setFloor(request.getParameter("Floor"));
            room.setRid(Integer.parseInt(request.getParameter("Rid")));

            String str;
            if (RoomDao.modifyRoom(room)) {
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
