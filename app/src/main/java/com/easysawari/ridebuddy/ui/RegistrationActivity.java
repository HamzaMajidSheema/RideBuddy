package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.easysawari.ridebuddy.R;
import com.easysawari.ridebuddy.models.RideSharer;
import com.easysawari.ridebuddy.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private EditText firstName;
    private EditText lastName;
    private String UID;
    private Button Registerbtn;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Registerbtn = findViewById(R.id.Registerbtn);

        RideSharer.RideSharerSignUp(RegistrationActivity.this,mDatabase,UID);

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.userId = UID;
                user.firstName = String.valueOf(firstName.getText());
                user.lastName = String.valueOf(lastName.getText());
                user.mobileNumber = getIntent().getStringExtra("phoneNumber");
                mDatabase.child("users")
                        .child(user.userId)
                        .setValue(user);

                Intent intent = new Intent(RegistrationActivity.this, Home.class);
                startActivity(intent);

            }
        });




    }


}
