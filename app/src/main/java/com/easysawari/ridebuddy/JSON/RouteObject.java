package com.easysawari.ridebuddy.JSON;



import org.json.JSONArray;

public class RouteObject {
    String title,time,carType,routeId;
    JSONArray startLoc,endLoc,stopList;

    public JSONArray getStopList() {
        return stopList;
    }

    public void setStopList(JSONArray stopList) {
        this.stopList = stopList;
    }

    public JSONArray getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(JSONArray startLoc) {
        this.startLoc = startLoc;
    }

    public JSONArray getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(JSONArray endLoc) {
        this.endLoc = endLoc;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
