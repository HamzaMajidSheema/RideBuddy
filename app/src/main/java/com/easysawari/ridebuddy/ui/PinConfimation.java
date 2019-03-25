package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;
import com.easysawari.ridebuddy.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PinConfimation extends AppCompatActivity {
    private EditText ed_PinNumber;
    private Button btn_ConfirmPin;
    private DatabaseReference mDatabase;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirmationpin);
        ed_PinNumber=(EditText)findViewById(R.id.ed_pin);
        btn_ConfirmPin=(Button) findViewById(R.id.btn_confirmPin);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent=getIntent();
        final String pin=intent.getStringExtra("Pin");
        final String name=intent.getStringExtra("Pin1");
        final String email=intent.getStringExtra("Pin2");
        final String password=intent.getStringExtra("Pin3");
        final String user_type=intent.getStringExtra("Pin4");
        final String mobileNumber=intent.getStringExtra("Pin5");
        final double rating=intent.getDoubleExtra("Pin6",0.0);
        //final String input_pin=ed_PinNumber.getText().toString();
        btn_ConfirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_pin=ed_PinNumber.getText().toString();
                if(pin.equals(input_pin)){
                    Toast.makeText(PinConfimation.this,"Pin Matched",Toast.LENGTH_SHORT).show();
                    user = new User();
                    String key = mDatabase.push().getKey().toString();
                    user.userId=key;
                    user.firstName=name;
                    user.user_email=email;
                    user.user_password=password;
                    user.userTyper=user_type;
                    user.mobileNumber=mobileNumber;
                    user.rating=rating;
                    mDatabase.child("users")
                            .child(user.userId)
                            .setValue(user);

                    Intent intent = new Intent(PinConfimation.this, Home.class);
                    intent.putExtra("key1",name);
                    intent.putExtra("key2",email);
                    intent.putExtra("key3",user_type);

                    startActivity(intent);
                    finish();
                    /*Intent intent1=new Intent(PinConfimation.this,RegistrationActivity.class);
                    intent1.putExtra("Key","ok");
                    startActivity(intent1);
                    finish();*/
                }
                else{
                    Toast.makeText(PinConfimation.this,"InCorrect Pin",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(PinConfimation.this,RegistrationActivity.class);
                    intent1.putExtra("Key","no");
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }
}
