package com.xiaobai.entity;

import java.math.BigDecimal;

public class BookSecModel {
    private Integer id;

    private Long secNum;

    public BookSecModel() {
    }

    public BookSecModel(Integer id, Long secNum) {
        this.id = id;
        this.secNum = secNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSecNum() {
        return secNum;
    }

    public void setSecNum(Long secNum) {
        this.secNum = secNum;
    }
}