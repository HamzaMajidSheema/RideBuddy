package com.easysawari.ridebuddy.ui;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Calendar;

public class SetSchedule extends AppCompatActivity {
    private GoogleMap sGoogleMap;
    private SupportMapFragment smapFrag;
    private LocationRequest sLocationRequest;
    private GoogleApiClient sGoogleApiClient;
    private Location sLastLocation;
    private  Marker sCurrLocationMarker;
    private Button btn_getDate,btn_getTime,btn_getEndTime,btn_SubmitSchdule;
    private TextView tv_start_time,tv_date,tv_endTime;
    private EditText ed_ArrivalLocation,ed_FinalLocation;
    private Spinner sp_noOfDays;
    AutocompleteSupportFragment splaceLocation , splaceDestination;
    String sPlaceLocation, sPlaceDestination;
    private boolean sLocationPermissionGranted;
    private static final String TAG2 = "Set Schedule";
    private String ArrivalLocation,FinalLocation;
    DatabaseReference mdatabaseuser;
    private CaptionTripSchedule captionTripSchedule;

    // Construct a FusedLocationProviderClient.
    FusedLocationProviderClient sFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setschedule);
        btn_getDate=(Button)findViewById(R.id.datePickerDialogButton);
        btn_getTime=(Button)findViewById(R.id.timePickerDialogButton);
        btn_getEndTime=(Button)findViewById(R.id.btn_endTime);
        btn_SubmitSchdule=(Button)findViewById(R.id.btn_submitSchdule);
        tv_start_time=(TextView)findViewById(R.id.timePickerValue);
        tv_endTime=(TextView)findViewById(R.id.timePickerValue2);
        tv_date=(TextView)findViewById(R.id.datePickerValue);
        ed_ArrivalLocation=(EditText)findViewById(R.id.ed_arrival);
        ed_FinalLocation=(EditText)findViewById(R.id.ed_final);
        sp_noOfDays=(Spinner)findViewById(R.id.sp_no_days);
        mdatabaseuser = FirebaseDatabase.getInstance().getReference("captionSchedule");
        Intent intent=getIntent();
        final String email=intent.getStringExtra("key");
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);


        sFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

         smapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        smapFrag.getMapAsync(this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyD3yaDZiY0l-kyX57r05Q0tGN_6dbjjkug");
        }
        PlacesClient placesClient = Places.createClient(this);

        //Destination finding
        splaceDestination = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.s_place_destination);
        splaceLocation = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.s_place_location);
        splaceLocation.setPlaceFields(Arrays.asList(Place.Field.ID));



        splaceDestination.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME , Place.Field.LAT_LNG , Place.Field.ADDRESS));
        splaceLocation.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG,Place.Field.ADDRESS));
        splaceLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                sPlaceLocation = place.getAddress();
                // remove old marker
                sGoogleMap.clear();

                //add marker on new pickup location
                sCurrLocationMarker = sGoogleMap.addMarker(new MarkerOptions().position( place.getLatLng())
                        .icon(BitmapDescriptorFactory.defaultMarker()).title("pickup here"));

                sGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),15.0f));
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG2, "An error occurred: " + status);

            }
        });
        splaceDestination.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                sPlaceDestination = place.getName();
                //add marker on new pickup location
                sCurrLocationMarker = sGoogleMap.addMarker(new MarkerOptions().position(place.getLatLng())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .title("Drop here"));

                sGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),15.0f));
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG2, "An error occurred: " + status);

            }
        });*/
        btn_getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append("You select date is ");
                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month+1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);
                        tv_date.setText(strBuf.toString());

                    }
                };
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetSchedule.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                datePickerDialog.setIcon(R.drawable.if_snowman);
                datePickerDialog.setTitle("Please select date.");

                // Popup the dialog.
                datePickerDialog.show();
            }
        });
        btn_getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // Create a new OnTimeSetListener instance. This listener will be invoked when user click ok button in TimePickerDialog.
                        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                StringBuffer strBuf = new StringBuffer();
                                strBuf.append("You select time is ");
                                strBuf.append(hour);
                                strBuf.append(":");
                                strBuf.append(minute);


                               tv_start_time.setText(strBuf.toString());
                            }
                        };

                        Calendar now = Calendar.getInstance();
                        int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
                        int minute = now.get(java.util.Calendar.MINUTE);

                        // Whether show time in 24 hour format or not.
                        boolean is24Hour = true;

                        TimePickerDialog timePickerDialog = new TimePickerDialog(SetSchedule.this, onTimeSetListener, hour, minute, is24Hour);

                        timePickerDialog.setIcon(R.drawable.if_snowman);
                        timePickerDialog.setTitle("Please select time.");

                        timePickerDialog.show();
            }
        });
        btn_getEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener2=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int  hour2, int minute) {
                        StringBuffer strBuf2 = new StringBuffer();
                        strBuf2.append("You select time is ");
                        strBuf2.append(hour2);
                        strBuf2.append(":");
                        strBuf2.append(minute);
                        tv_endTime.setText(strBuf2.toString());


                    }
                };
                Calendar now = Calendar.getInstance();
                int hour2 = now.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = now.get(java.util.Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = true;

                TimePickerDialog timePickerDialog = new TimePickerDialog(SetSchedule.this, onTimeSetListener2, hour2, minute, is24Hour);

                timePickerDialog.setIcon(R.drawable.if_snowman);
                timePickerDialog.setTitle("Please select time.");

                timePickerDialog.show();

            }
        });
        btn_SubmitSchdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StartLocation, EndLocation, StartDate, TripStartTime, TripEndTIme, NoOFdays;
                StartLocation = ed_ArrivalLocation.getText().toString().trim();
                EndLocation = ed_FinalLocation.getText().toString().trim();
                StartDate = tv_date.getText().toString().trim();
                TripStartTime = tv_start_time.getText().toString().trim();
                TripEndTIme = tv_endTime.getText().toString().trim();
                NoOFdays = sp_noOfDays.getSelectedItem().toString().trim();
                if (TextUtils.isEmpty(StartLocation)) {
                    Toast.makeText(getApplicationContext(), "Enter The Start Location ", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(EndLocation)) {
                    Toast.makeText(getApplicationContext(), "Enter End Location", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (TextUtils.isEmpty(StartDate)) {
                    Toast.makeText(getApplicationContext(), "Enter Start Date !", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(TripStartTime)) {
                    Toast.makeText(getApplicationContext(), "Enter The Trip Start Time ", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(TripEndTIme)) {
                    Toast.makeText(getApplicationContext(), "Enter The Trip End Time", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (TextUtils.isEmpty(NoOFdays)) {
                    Toast.makeText(getApplicationContext(), "Enter No Of Days !", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (!TextUtils.isEmpty(StartLocation) && (!TextUtils.isEmpty(EndLocation) && !TextUtils.isEmpty(StartDate) && (!TextUtils.isEmpty(TripStartTime) && (!TextUtils.isEmpty(TripEndTIme) && !TextUtils.isEmpty(NoOFdays))))) {
                    String id = mdatabaseuser.push().getKey();


                    CaptionTripSchedule captionTripSchedule = new CaptionTripSchedule(email, id, StartLocation, EndLocation, StartDate, TripStartTime, TripEndTIme, NoOFdays);
                    mdatabaseuser.child(id).setValue(captionTripSchedule);
                    Toast.makeText(SetSchedule.this,"Your Trip Sechdule Is Submitted ,Enter The new trip Schedule,or visit Trips to Change Schedule",Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(SetSchedule.this,"Unable to Submit the Trip Location",Toast.LENGTH_SHORT).show();
                }
            }



        });

    }


    /*@Override
    public void onConnected(@Nullable Bundle bundle) {
        sLocationRequest = new LocationRequest();
        sLocationRequest.setInterval(1000);
        sLocationRequest.setFastestInterval(1000);
        sLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(sGoogleApiClient,sLocationRequest,this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        sLastLocation = location;
        if (sCurrLocationMarker != null) {
            sCurrLocationMarker.remove();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        sGoogleMap=googleMap;
        sGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                sGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }

        }
        else {
            buildGoogleApiClient();
            sGoogleMap.setMyLocationEnabled(true);
        }

        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation1();

    }
    private void getDeviceLocation1() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.

        try {
            if (sLocationPermissionGranted) {
                Task locationResult = sFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            sLastLocation = (Location) task.getResult();
                            sGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(sLastLocation.getLatitude(),
                                            sLastLocation.getLongitude()), 12));
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    private void updateLocationUI() {
        if (sGoogleMap == null) {
            return;
        }
        try {
            if (sLocationPermissionGranted) {
                sGoogleMap.setMyLocationEnabled(true);
                sGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                sGoogleMap.setMyLocationEnabled(false);
                sGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                sLastLocation = null;
                checkLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    protected synchronized void buildGoogleApiClient() {
        sGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        sGoogleApiClient.connect();
    }
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 99;
    private void checkLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
       /* if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            sLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        sLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }*/
}
