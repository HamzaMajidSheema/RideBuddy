package com.easysawari.ridebuddy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;
import com.easysawari.ridebuddy.Utils.PhoneAuth;
import com.easysawari.ridebuddy.models.RideSharer;
import com.easysawari.ridebuddy.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {



    private EditText editText;
    private ProgressBar progressBar;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        progressBar = findViewById(R.id.progressbar);
        editText = findViewById(R.id.editTextCode);
        signIn = findViewById(R.id.buttonSignIn);
        final String phonenumber = getIntent().getStringExtra("phonenumber");

        RideSharer.RideSharerSignIn(this,progressBar,editText,signIn,phonenumber);

    }


}
