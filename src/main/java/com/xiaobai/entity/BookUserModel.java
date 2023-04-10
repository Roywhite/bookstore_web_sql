package com.xiaobai.entity;

public class BookUserModel {

    private Integer id;

    private String name;

    private Long account;

    private String password;

    private Long backKey;

    private Long score;

    private String receiverAddress;

    private int booleanRent;

    private Long currentOrderNum;

    private Long residueDegree;

    public BookUserModel(Integer id, String name, Long account, String password, Long backKey, Long score, String receiverAddress, int booleanRent, Long currentOrderNum, Long residueDegree) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.backKey = backKey;
        this.score = score;
        this.receiverAddress = receiverAddress;
        this.booleanRent = booleanRent;
        this.currentOrderNum = currentOrderNum;
        this.residueDegree = residueDegree;
    }

    public BookUserModel() {
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Long getResidueDegree() {
        return residueDegree;
    }

    public void setResidueDegree(Long residueDegree) {
        this.residueDegree = residueDegree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBackKey() {
        return backKey;
    }

    public void setBackKey(Long backKey) {
        this.backKey = backKey;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public int getBooleanRent() {
        return booleanRent;
    }

    public void setBooleanRent(int booleanRent) {
        this.booleanRent = booleanRent;
    }

    public Long getCurrentOrderNum() {
        return currentOrderNum;
    }

    public void setCurrentOrderNum(Long currentOrderNum) {
        this.currentOrderNum = currentOrderNum;
    }
}