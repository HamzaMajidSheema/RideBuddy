package com.easysawari.ridebuddy.models;

/**
 * Created by Redpoixon on 12/12/18.
 */

public class Ride {
    public String rideId;
    public RideSharer rideSharer;
    public VehicleOwner vehicleOwner;
    public float distance;
    public float duration;
    public String dropOffLocation;
    public String pickUpLocation;
    public float fare;
}
