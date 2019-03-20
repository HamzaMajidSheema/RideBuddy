package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    private EditText userEmail,userPassword,userPhone;
    private Spinner sp_type;
    private String UID;
    private Button Registerbtn;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = findViewById(R.id.firstName);
        userEmail = findViewById(R.id.ed_useremail);
        userPassword = findViewById(R.id.ed_userpassword);
        userPhone = findViewById(R.id.ed_userPhone);
        sp_type=findViewById(R.id.spinner1);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Registerbtn = findViewById(R.id.Registerbtn);

       // RideSharer.RideSharerSignUp(RegistrationActivity.this,mDatabase,UID);

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                String key = mDatabase.push().getKey().toString();
                user.userId=key;
                user.firstName = String.valueOf(firstName.getText());
                user.user_email = String.valueOf(userEmail.getText());
                user.user_password = String.valueOf(userPassword.getText());
                user.userTyper=sp_type.getSelectedItem().toString();
                user.mobileNumber=String.valueOf(userPhone.getText());
                //user.mobileNumber = getIntent().getStringExtra("phoneNumber");
                mDatabase.child("users")
                        .child(user.userId)
                        .setValue(user);

                Intent intent = new Intent(RegistrationActivity.this, Home.class);
                intent.putExtra("key1",String.valueOf(firstName.getText()));
                intent.putExtra("key2",String.valueOf(userEmail.getText()));
                intent.putExtra("key3",user.userTyper);

                startActivity(intent);

            }
        });




    }


}
