package com.xiaobai.entity;

public class BookLocationModel {
    private Integer id;

    private String locationName;

    private String locationFullName;

    public BookLocationModel() {
    }

    public BookLocationModel(Integer id, String locationName, String locationFullName) {
        this.id = id;
        this.locationName = locationName;
        this.locationFullName = locationFullName;
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
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getLocationFullName() {
        return locationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        this.locationFullName = locationFullName == null ? null : locationFullName.trim();
    }
}