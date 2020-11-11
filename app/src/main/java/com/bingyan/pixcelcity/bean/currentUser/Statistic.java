package com.bingyan.pixcelcity.bean.currentUser;

public class Statistic {

    //暂未处理
    String detail_data;

    int total_explore_city_count;

    int total_explore_count;

    int total_explore_country_count;

    public Statistic() {
    }

    public Statistic(String detail_data, int total_explore_city_count, int total_explore_count, int total_explore_country_count) {
        this.detail_data = detail_data;
        this.total_explore_city_count = total_explore_city_count;
        this.total_explore_count = total_explore_count;
        this.total_explore_country_count = total_explore_country_count;
    }

    public String getDetail_data() {
        return detail_data;
    }

    public void setDetail_data(String detail_data) {
        this.detail_data = detail_data;
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

    public int getTotal_explore_country_count() {
        return total_explore_country_count;
    }

    public void setTotal_explore_country_count(int total_explore_country_count) {
        this.total_explore_country_count = total_explore_country_count;
    }
}
