package com.example.hsm.beans;

import java.util.ArrayList;

public class Room {
    //基本
    private Integer Rid;
    private String Floor;
    private String Feature;
    private String Orientation;
    //外键
    private Type type;
    //衍射索引
    private ArrayList<Book> Books;

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer rid) {
        Rid = rid;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }

    public String getOrientation() {
        return Orientation;
    }

    public void setOrientation(String orientation) {
        Orientation = orientation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public void setBooks(ArrayList<Book> books) {
        Books = books;
    }
}
