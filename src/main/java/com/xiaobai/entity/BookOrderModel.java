package com.xiaobai.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BookOrderModel {
    private Integer id;

    private Long account;

    private Long orderNum;

    private Long bookCoding;

    private String bookName;

    private String rentLocationName;

    private Date timeGenerate;

    private Long currentScore;

    private Long residueDay;

    private String receiverAddress;

    private Integer booleanTalk;

    public BookOrderModel() {
    }

    public BookOrderModel(Integer id, Long account, Long orderNum, Long bookCoding, String bookName, String rentLocationName, Date timeGenerate, Long currentScore, Long residueDay, String receiverAddress, Integer booleanTalk) {
        this.id = id;
        this.account = account;
        this.orderNum = orderNum;
        this.bookCoding = bookCoding;
        this.bookName = bookName;
        this.rentLocationName = rentLocationName;
        this.timeGenerate = timeGenerate;
        this.currentScore = currentScore;
        this.residueDay = residueDay;
        this.receiverAddress = receiverAddress;
        this.booleanTalk = booleanTalk;
    }

    public Integer getBooleanTalk() {
        return booleanTalk;
    }

    public void setBooleanTalk(Integer booleanTalk) {
        this.booleanTalk = booleanTalk;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Long getResidueDay() {
        return residueDay;
    }

    public void setResidueDay(Long residueDay) {
        this.residueDay = residueDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getBookCoding() {
        return bookCoding;
    }

    public void setBookCoding(Long bookCoding) {
        this.bookCoding = bookCoding;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getRentLocationName() {
        return rentLocationName;
    }

    public void setRentLocationName(String rentLocationName) {
        this.rentLocationName = rentLocationName;
    }

    public Date getTimeGenerate() {
        return timeGenerate;
    }

    public void setTimeGenerate(Date timeGenerate) {
        this.timeGenerate = timeGenerate;
    }

    public Long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Long currentScore) {
        this.currentScore = currentScore;
    }
}