package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;
import com.easysawari.ridebuddy.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {


    private EditText ed_UserName,ed_UserPassword;
    private Spinner sp_LogUserType;
    private Button buttonContinue,btn_UserRegister;
    SessionManagement session;
    DatabaseReference databaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_UserName = findViewById(R.id.editTextPhone);
        ed_UserPassword = findViewById(R.id.ed_LogPass);
        sp_LogUserType=(Spinner)findViewById(R.id.sp_LogInType);
        btn_UserRegister=(Button)findViewById(R.id.btn_UserRegister);

        session = new SessionManagement(getApplicationContext());

        buttonContinue = findViewById(R.id.buttonContinue);

                buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String number = ed_UserName.getText().toString().trim();
                final String password = ed_UserPassword.getText().toString().trim();
                final String type = sp_LogUserType.getSelectedItem().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    ed_UserName.setError("Valid number is required");
                    ed_UserName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter the password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(getApplicationContext(), "Enter the login Type ", Toast.LENGTH_SHORT).show();
                    return;
                }
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("users");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user=dataSnapshot.getValue(User.class);
                        for(DataSnapshot ref : dataSnapshot.getChildren()) {
                            HashMap<String, String> value = (HashMap<String, String>) ref.getValue();
                            String email1 = value.get("user_email");

                            String password1 = value.get("user_password");
                            String username1 = value.get("firstName");
                            String usertype1=value.get("userTyper");
                            String phonenumber=value.get("mobileNumber");
                            if (number.equals(phonenumber)|| number.equals(email1)||!TextUtils.isEmpty(phonenumber)||!TextUtils.isEmpty(email1)) {
                                if (password.equals(password1)) {
                                    if(type.equals(usertype1)) {

                                            Toast.makeText(LoginActivity.this, "Sucessfully login" + "with this" + email1, Toast.LENGTH_SHORT).show();
                                            session.createLoginSession(username1,email1,usertype1);
                                            Intent intent = new Intent(LoginActivity.this, Home.class);
                                            // session = new Session(LoginActivity.this);
                                            //session.setusename(username1);
                                            //session.setusename(email1);
                                            //session.setusename(usertype1);

                                            startActivity(intent);
                                        }


                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,"Incorrect Information Please Re Enter the Inforation",Toast.LENGTH_SHORT).show();
                            }
                            }
                        }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //String phoneNumber = "+92"  + number;
               // Toast.makeText(LoginActivity.this, phoneNumber, Toast.LENGTH_LONG).show();


                //Intent intent = new Intent(LoginActivity.this, Home.class);
                //intent.putExtra("phonenumber", phoneNumber);
                //startActivity(intent);

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

}
