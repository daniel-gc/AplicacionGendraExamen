package com.gendra.appgendraexamen.webservices.response;

import com.gendra.appgendraexamen.data.model.Geometry;

public class ResponsePolygon {
    private String type;
    private Geometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
