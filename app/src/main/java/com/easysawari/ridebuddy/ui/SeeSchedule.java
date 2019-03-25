package com.easysawari.ridebuddy.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.easysawari.ridebuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeeSchedule extends AppCompatActivity {
    private RecyclerView TripRecycler;
    DatabaseReference reference;
    public static final String FB_DATABASE_PATH = "captionSchedule";
    ArrayList<OwnerTripDetail> istitem1=new ArrayList<>();

    SeeScheduleAdapter seeScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_seeschedule);
        TripRecycler=(RecyclerView)findViewById(R.id.rec_OwnerTrip);
        reference = FirebaseDatabase.getInstance().getReference(SeeSchedule.FB_DATABASE_PATH);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        ;
        TripRecycler.setHasFixedSize(true);
        TripRecycler.setLayoutManager(mLayoutManager);
        TripRecycler.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String id=snapshot.child("id").getValue(String.class);

                        String email = snapshot.child("email").getValue(String.class);
                        String startLocation = snapshot.child("startLocation").getValue(String.class);
                        String endLocation = snapshot.child("endLocation").getValue(String.class);
                        String startTime = snapshot.child("tripStartTime").getValue(String.class);
                    String endTime = snapshot.child("tripEndTime").getValue(String.class);
                    String startDate = snapshot.child("date").getValue(String.class);
                    String noOfDays = snapshot.child("noOfDays").getValue(String.class);
                    OwnerTripDetail ownerTripDetail = snapshot.getValue(OwnerTripDetail.class);
                    istitem1.add(ownerTripDetail);

                }
                seeScheduleAdapter= new SeeScheduleAdapter(SeeSchedule.this,istitem1);
                TripRecycler.setAdapter(seeScheduleAdapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
