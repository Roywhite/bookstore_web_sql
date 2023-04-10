package com.xiaobai.entity;


public class BookBooksModel {
    private Integer id;

    private String locationName;

    private Long booksCoding;

    private String booksName;

    private String booksLocation;

    private Long booksNum;

    private String booksProfile;

    private Long booksRentNum;

    private Double booksScore;

    public BookBooksModel() {
    }

    public BookBooksModel(Integer id, String locationName, Long booksCoding, String booksName, String booksLocation, Long booksNum, String booksProfile, Long booksRentNum, Double booksScore) {
        this.id = id;
        this.locationName = locationName;
        this.booksCoding = booksCoding;
        this.booksName = booksName;
        this.booksLocation = booksLocation;
        this.booksNum = booksNum;
        this.booksProfile = booksProfile;
        this.booksRentNum = booksRentNum;
        this.booksScore = booksScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getBooksCoding() {
        return booksCoding;
    }

    public void setBooksCoding(Long booksCoding) {
        this.booksCoding = booksCoding;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }

    public String getBooksLocation() {
        return booksLocation;
    }

    public void setBooksLocation(String booksLocation) {
        this.booksLocation = booksLocation;
    }

    public Long getBooksNum() {
        return booksNum;
    }

    public void setBooksNum(Long booksNum) {
        this.booksNum = booksNum;
    }

    public String getBooksProfile() {
        return booksProfile;
    }

    public void setBooksProfile(String booksProfile) {
        this.booksProfile = booksProfile;
    }

    public Long getBooksRentNum() {
        return booksRentNum;
    }

    public void setBooksRentNum(Long booksRentNum) {
        this.booksRentNum = booksRentNum;
    }

    public Double getBooksScore() {
        return booksScore;
    }

    public void setBooksScore(Double booksScore) {
        this.booksScore = booksScore;
    }
}