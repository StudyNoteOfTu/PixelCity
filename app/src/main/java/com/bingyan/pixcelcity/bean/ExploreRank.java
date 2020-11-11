package com.bingyan.pixcelcity.bean;

public class ExploreRank {

    String id;
    long createdAt;
    long deleteAt;
    int status;
    String userId;
    String username;
    String locationId;
    String country;
    String city;
    int rank;

    public ExploreRank() {
    }

    public ExploreRank(String id, long createdAt, long deleteAt, int status, String userId, String username, String locationId, String country, String city, int rank) {
        this.id = id;
        this.createdAt = createdAt;
        this.deleteAt = deleteAt;
        this.status = status;
        this.userId = userId;
        this.username = username;
        this.locationId = locationId;
        this.country = country;
        this.city = city;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(long deleteAt) {
        this.deleteAt = deleteAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
