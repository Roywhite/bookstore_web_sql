package com.xiaobai.entity;

import java.util.Date;

public class BookFriendTalkModel {
    private Integer id;

    private Long account;

    private String userName;

    private String bookName;

    private String talk;

    private Date generateTime;

    public BookFriendTalkModel() {
    }

    public BookFriendTalkModel(Integer id, Long account, String userName, String bookName, String talk, Date generateTime) {
        this.id = id;
        this.account = account;
        this.userName = userName;
        this.bookName = bookName;
        this.talk = talk;
        this.generateTime = generateTime;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk == null ? null : talk.trim();
    }
}