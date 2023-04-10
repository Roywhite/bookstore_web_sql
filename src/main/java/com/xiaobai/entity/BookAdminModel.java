package com.xiaobai.entity;


public class BookAdminModel {
    private Long adminAccount;

    private String adminPassword;

    public BookAdminModel() {
    }

    public BookAdminModel(Long adminAccount, String adminPassword) {
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
    }

    public Long getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(Long adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}