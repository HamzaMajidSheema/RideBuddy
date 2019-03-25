package com.easysawari.ridebuddy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;

public class UpdateTrip extends AppCompatActivity {
    private TextView tv_startlaocation,tv_endlocation,tv_starttime,tv_endtime,tv_date,tv_noofdays;
    private EditText ed_startlocation,ed_endlocation,ed_starttime,ed_endtime,ed_noofdays;
    private Button btn_updateTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modifyschedule);
        tv_startlaocation=(TextView)findViewById(R.id.tv_startlocation1);
        tv_endlocation=(TextView)findViewById(R.id.tv_endlocation1);
        tv_starttime=(TextView)findViewById(R.id.tv_StartTime1);
        tv_endtime=(TextView)findViewById(R.id.tv_EndTime1);
        tv_date=(TextView)findViewById(R.id.tv_date1);
        tv_noofdays=(TextView)findViewById(R.id.tv_noOfDays1);
        ed_startlocation=(EditText)findViewById(R.id.ed_pickup);
        ed_endlocation=(EditText)findViewById(R.id.ed_final);
        ed_starttime=(EditText)findViewById(R.id.ed_starttime);
        ed_starttime=(EditText)findViewById(R.id.ed_endtime);
        ed_endtime=(EditText)findViewById(R.id.ed_date);
        ed_noofdays=(EditText)findViewById(R.id.ed_noOFdays);
        btn_updateTrip=(Button)findViewById(R.id.btn_UpdateTrip);
        final String Tripid1=getIntent().getExtras().getString("key1");
        final String StartLocation2=getIntent().getExtras().getString("key2");
        final String EndLocation2=getIntent().getExtras().getString("key3");
        final String StartTime2=getIntent().getExtras().getString("key4");
        final String EndTime2=getIntent().getExtras().getString("key5");
        final String Date2=getIntent().getExtras().getString("key6");
        final String noofdays2=getIntent().getExtras().getString("key7");
        tv_startlaocation.setText(StartLocation2);
        tv_endlocation.setText(EndLocation2);
        tv_starttime.setText(StartTime2);
        tv_endtime.setText(EndTime2);
        tv_date.setText(Date2);
        tv_noofdays.setText(noofdays2);
        btn_updateTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateTrip.this,"Your Trip Is Updated",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
