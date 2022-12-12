package com.example.hsm.beans;

public class Type {
    //基本类型
    private Integer Tid;
    private String TypeName;
    private String TypePrice;
    //衍生
    private Integer EmptyRoom;
    private Integer OrderedRoom;

    public Integer getTid() {
        return Tid;
    }

    public void setTid(Integer tid) {
        Tid = tid;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getTypePrice() {
        return TypePrice;
    }

    public void setTypePrice(String typePrice) {
        TypePrice = typePrice;
    }

    public Integer getEmptyRoom() {
        return EmptyRoom;
    }

    public void setEmptyRoom(Integer emptyRoom) {
        EmptyRoom = emptyRoom;
    }

    public Integer getOrderedRoom() {
        return OrderedRoom;
    }

    public void setOrderedRoom(Integer orderedRoom) {
        OrderedRoom = orderedRoom;
    }
}
