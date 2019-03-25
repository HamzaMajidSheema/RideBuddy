package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;

public class RIdeOwner extends AppCompatActivity {
    private TextView tv_name,tv_email,tv_type;
    private Button btn_updateProfile,btn_setSechdule,btn_signOut,btn_seeSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.ride_owner);
       tv_name=(TextView)findViewById(R.id.tv_username);
       tv_email=(TextView)findViewById(R.id.tv_useremail);
       tv_type=(TextView)findViewById(R.id.tv_usertype);
       btn_updateProfile=(Button)findViewById(R.id.btn_updateProfile);
        btn_setSechdule=(Button)findViewById(R.id.btn_setSecdule);
        btn_seeSchedule=(Button)findViewById(R.id.btn_SeeSchedule);
        btn_signOut=(Button)findViewById(R.id.btn_SignOut);
        Intent intent2=getIntent();
        final String username=intent2.getStringExtra("key1");
        final String email=intent2.getStringExtra("key2");
        final String type=intent2.getStringExtra("key3");
        tv_name.setText(username);
        tv_email.setText(email);
        tv_type.setText(type);
        btn_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RIdeOwner.this,"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        btn_setSechdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RIdeOwner.this,SetSchedule.class);
                intent.putExtra("key",email);
                startActivity(intent);

            }
        });
        btn_seeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RIdeOwner.this,SeeSchedule.class);
                startActivity(intent);

            }
        });
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_name.setText("");
                tv_email.setText("");
                tv_type.setText("");
                Intent intent3= new Intent(RIdeOwner.this,LoginActivity.class);
                startActivity(intent3);

            }
        });
    }
}
