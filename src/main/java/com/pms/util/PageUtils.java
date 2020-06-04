package com.pms.util;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public class PageUtils implements Serializable {
    private int begin;
    private int end;

    public PageUtils(int offset, int limit) {
        this.begin = (offset-1)*limit;
        this.end = begin+limit;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}
