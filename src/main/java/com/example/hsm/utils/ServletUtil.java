package com.example.hsm.utils;

import com.example.hsm.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ServletUtil {
    public static void setCharsetJSON(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserType(HttpServletRequest request,String UserType){
        User user =(User) request.getSession().getAttribute(SessionAttribute.UserAttribute);
        if (user==null){
            return false;
        }else {
            return UserType.equals(user.getUserType());
        }
    }

    public  static  boolean checkLogin(HttpServletRequest request){
        User user =(User) request.getSession().getAttribute(SessionAttribute.UserAttribute);
        return user != null;
    }
}
