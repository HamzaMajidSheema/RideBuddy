package com.easysawari.ridebuddy.models;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.easysawari.ridebuddy.Utils.PhoneAuth;
import com.easysawari.ridebuddy.ui.ProfileActivity;
import com.easysawari.ridebuddy.ui.RegistrationActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Redpoixon on 12/12/18.
 */

public class RideSharer {
    public User user;

   public  RideSharer(){
        user = new User();
    }

    public static void RideSharerSignUp(final Activity activity, Button signUp, final DatabaseReference mDatabase, final User user)
    {
        mDatabase.child("users")
                .child(user.userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        if(user != null)
                        {
                            Intent intent = new Intent(activity, ProfileActivity.class);
                            activity.startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("users")
                        .child(user.userId)
                        .setValue(user);

                Intent intent = new Intent(activity, ProfileActivity.class);
                activity.startActivity(intent);

            }
        });



    }

    public static void RideSharerSignIn(final Activity activity, ProgressBar progressBar, final EditText editText , Button signIn, String phonenumber)
    {

        final PhoneAuth PA = new PhoneAuth(activity,progressBar,editText);
        PA.sendVerificationCode(phonenumber);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                PA.verifyCode(code);
            }
        });
    }


}
