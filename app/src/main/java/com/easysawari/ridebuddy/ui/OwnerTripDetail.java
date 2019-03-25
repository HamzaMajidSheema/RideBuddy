package com.easysawari.ridebuddy.ui;

public class OwnerTripDetail {
    private String id;
    private String email;
    private String startLocation;
    private String endLocation;
    private String tripStartTime;
    private String tripEndTime;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCaptionEmail() {
        return captionEmail;
    }

    public void setCaptionEmail(String captionEmail) {
        this.captionEmail = captionEmail;
    }

    private String startDate;
    private String noOfDays;
    private String date;
    private String captionEmail;

    public OwnerTripDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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



    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public OwnerTripDetail(String id, String email, String startLocation, String endLocation, String tripStartTime, String tripEndTime, String date, String noOfDays) {
        this.id = id;
        this.email = email;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.tripStartTime = tripStartTime;
        this.tripEndTime = tripEndTime;
        this.date = date  ;
        this.noOfDays = noOfDays;
    }
}
