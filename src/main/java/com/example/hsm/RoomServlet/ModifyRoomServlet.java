package com.example.hsm.RoomServlet;

import com.example.hsm.beans.Room;
import com.example.hsm.beans.Type;
import com.example.hsm.beans.User;
import com.example.hsm.dao.RoomDao;
import com.example.hsm.dao.TypeDao;
import com.example.hsm.utils.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ModifyRoomServlet", value = "/ModifyRoomServlet")
public class ModifyRoomServlet extends HttpServlet {
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

            Room room = new Room();
            room.setType(type);
            room.setOrientation(request.getParameter(request.getParameter("Orientation")));
            room.setFeature(request.getParameter("Feature"));
            room.setFloor(request.getParameter("Floor"));
            room.setRid(Integer.parseInt(request.getParameter("Rid")));

            String s;
            if (RoomDao.modifyRoom(room)) {
                s = "修改成功";
            } else {
                s = "修改失败";
            }

            PrintWriter writer = response.getWriter();
            writer.print(s);
        }
    }
}
