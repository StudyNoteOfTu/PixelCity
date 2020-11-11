package com.bingyan.pixcelcity.bean;

public class Location {

    String locationId;
    long createdAt;
    long deleteAt;
    int status;
    String userId;
    String username;
    String locationPicture;
    String text;
    String country;
    String city;
    String locationName;
    double longitude;
    double latitude;
    int totalExploredCount;
    int totalWantedCount;

    String featureInfo;

    public Location() {
    }

    public Location(String locationId, long createdAt, long deleteAt, int status, String userId, String username, String locationPicture, String text, String country, String city, String locationName, double longitude, double latitude, int totalExploredCount, int totalWantedCount) {
        this.locationId = locationId;
        this.createdAt = createdAt;
        this.deleteAt = deleteAt;
        this.status = status;
        this.userId = userId;
        this.username = username;
        this.locationPicture = locationPicture;
        this.text = text;
        this.country = country;
        this.city = city;
        this.locationName = locationName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalExploredCount = totalExploredCount;
        this.totalWantedCount = totalWantedCount;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    public String getLocationPicture() {
        return locationPicture;
    }

    public void setLocationPicture(String locationPicture) {
        this.locationPicture = locationPicture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getTotalExploredCount() {
        return totalExploredCount;
    }

    public void setTotalExploredCount(int totalExploredCount) {
        this.totalExploredCount = totalExploredCount;
    }

    public int getTotalWantedCount() {
        return totalWantedCount;
    }

    public void setTotalWantedCount(int totalWantedCount) {
        this.totalWantedCount = totalWantedCount;
    }

    public String getFeatureInfo() {
        return featureInfo;
    }

    public void setFeatureInfo(String featureInfo) {
        this.featureInfo = featureInfo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId='" + locationId + '\'' +
                ", createdAt=" + createdAt +
                ", deleteAt=" + deleteAt +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", locationPicture='" + locationPicture + '\'' +
                ", text='" + text + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", locationName='" + locationName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", totalExploredCount=" + totalExploredCount +
                ", totalWantedCount=" + totalWantedCount +
                ", featureInfo='" + featureInfo + '\'' +
                '}';
    }
}
