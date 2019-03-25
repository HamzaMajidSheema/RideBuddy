package com.easysawari.ridebuddy.ui;

public class CaptionTripSchedule {
    String captionEmail;
    String id;
    String startLocation;
    String endLocation;
    String date;
    String tripStartTime;
    String tripEndTime;
    String noOfDays;

    public CaptionTripSchedule(String captionEmail, String id, String startLocation, String endLocation, String date, String tripStartTime, String tripEndTime, String noOfDays) {
        this.captionEmail = captionEmail;
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.date = date;
        this.tripStartTime = tripStartTime;
        this.tripEndTime = tripEndTime;
        this.noOfDays = noOfDays;
    }

    public String getCaptionEmail() {
        return captionEmail;
    }

    public void setCaptionEmail(String captionEmail) {
        this.captionEmail = captionEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public String getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(String tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String NoOfDays) {
        this.noOfDays = noOfDays;
    }
}

