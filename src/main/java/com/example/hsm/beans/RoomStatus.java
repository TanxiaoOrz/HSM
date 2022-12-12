package com.example.hsm.beans;

public class RoomStatus {
    private Integer EmptyCount;
    private Integer OrderedCount;

    public Integer getEmptyCount() {
        return EmptyCount;
    }

    public void setEmptyCount(Integer emptyCount) {
        EmptyCount = emptyCount;
    }

    public Integer getOrderedCount() {
        return OrderedCount;
    }

    public void setOrderedCount(Integer orderedCount) {
        OrderedCount = orderedCount;
    }
}
