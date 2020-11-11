package com.bingyan.pixcelcity.bean.currentUser;

public class Profile {

    String avatar;

    String city;

    String id;

    String telephone;

    int total_explore_city_count;

    int total_explore_count;

    int total_want_count;

    String username;

    public Profile() {
    }

    public Profile(String avatar, String city, String id, String telephone, int total_explore_city_count, int total_explore_count, int total_want_count, String username) {
        this.avatar = avatar;
        this.city = city;
        this.id = id;
        this.telephone = telephone;
        this.total_explore_city_count = total_explore_city_count;
        this.total_explore_count = total_explore_count;
        this.total_want_count = total_want_count;
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getTotal_explore_city_count() {
        return total_explore_city_count;
    }

    public void setTotal_explore_city_count(int total_explore_city_count) {
        this.total_explore_city_count = total_explore_city_count;
    }

    public int getTotal_explore_count() {
        return total_explore_count;
    }

    public void setTotal_explore_count(int total_explore_count) {
        this.total_explore_count = total_explore_count;
    }

    public int getTotal_want_count() {
        return total_want_count;
    }

    public void setTotal_want_count(int total_want_count) {
        this.total_want_count = total_want_count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
