package com.easysawari.ridebuddy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.easysawari.ridebuddy.R;
import com.easysawari.ridebuddy.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        firstName = (EditText) findViewById(R.id.fName);
        lastName = (EditText) findViewById(R.id.lName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        updateBtn = (Button) findViewById(R.id.UpdateBtn);


        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users")
                .child(UID)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);
                       firstName.setText(user.firstName);
                       lastName.setText(user.user_email);
                       phoneNumber.setText(user.mobileNumber);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                Map<String, Object> Updates = new HashMap<>();
                Updates.put("firstName", firstName.getText());
                Updates.put("lastName", lastName.getText());
                Updates.put("mobileNumber", phoneNumber.getText());

                mDatabase.child(UID).updateChildren(Updates);

            }
        });

    }
}
