package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.util.Random;

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
                user.rating=5.0;
                Random r = new Random();
                int randomNumber = r.nextInt((9999-1000+1))+1000;
                Integer intInstance = new Integer(randomNumber);
                String numberAsString = intInstance.toString();

                try { SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(String.valueOf(userPhone.getText()), null, numberAsString, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this,PinConfimation.class);
                    intent.putExtra("Pin",numberAsString);
                    intent.putExtra("Pin1",user.firstName);
                    intent.putExtra("Pin2",user.user_email);
                    intent.putExtra("Pin3",user.user_password);
                    intent.putExtra("Pin4",user.userTyper);
                    intent.putExtra("Pin5",user.mobileNumber);
                    intent.putExtra("Pin6",user.rating);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"SMS failed, please try again.", Toast.LENGTH_LONG).show(); e.printStackTrace(); }

                //RequiresPermission.Read more: http://mrbool.com/android-message-how-to-send-receive-sms-using-the-built-in-messaging-application-in-android/31138#ixzz5ijM0LgLd
                //user.mobileNumber = getIntent().getStringExtra("phoneNumber");
                Intent intent1=getIntent();
                String confirm=intent1.getStringExtra("Key");
                 if(confirm!=null && confirm.equalsIgnoreCase("no")){
                    Toast.makeText(RegistrationActivity.this,"Want to try again",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }


}
