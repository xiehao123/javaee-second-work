package com.xiehao.gym.domain;

import lombok.Data;

@Data
public class Info {
    private String lname;
    private double price;
    private String coachname;
    private String phone;
    private int sold;

    public Info(String lname, double price, String coachname, String phone, int sold) {
        this.lname = lname;
        this.price = price;
        this.coachname = coachname;
        this.phone = phone;
        this.sold = sold;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCoachname() {
        return coachname;
    }

    public void setCoachname(String coachname) {
        this.coachname = coachname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
