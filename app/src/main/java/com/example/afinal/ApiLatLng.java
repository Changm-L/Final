package com.example.afinal;

import com.google.android.gms.maps.model.LatLng;

public class ApiLatLng {
    String name;
    LatLng geo;

    public ApiLatLng(String name, LatLng geo) {
        this.name = name;
        this.geo = geo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getGeo() {
        return geo;
    }

    public void setGeo(LatLng geo) {
        this.geo = geo;
    }
}
