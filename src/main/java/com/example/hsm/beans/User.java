package com.example.hsm.beans;

public class User {

    public static String TypeClient = "client";
    public static String TypeReception = "reception";
    public static String TypeManager = "manager";
    private Integer Uid;
    private String UserCode;
    private String UserPass;
    private String UserType;

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String userPass) {
        UserPass = userPass;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
