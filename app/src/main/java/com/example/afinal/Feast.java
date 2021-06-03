package com.example.afinal;

public class Feast {
    private String name;
    private String date;
    private String coast;


    public Feast(String name, String date, String coast) {
        this.name = name;
        this.date = date;
        this.coast = coast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }
}
