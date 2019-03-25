package com.easysawari.ridebuddy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easysawari.ridebuddy.R;

public class Customer extends AppCompatActivity {

    private TextView tv_cusname,tv_cusemail,tv_custype;
    private Button btn_cusupdateProfile,btn_cussetSechdule,btn_cussignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);
        tv_cusname=(TextView)findViewById(R.id.tv_Cususername);
        tv_cusemail=(TextView)findViewById(R.id.tv_Cususeremail);
        tv_custype=(TextView)findViewById(R.id.tv_Cususertype);
        btn_cusupdateProfile=(Button)findViewById(R.id.btn_cusupdateProfile);
        btn_cussetSechdule=(Button)findViewById(R.id.btn_seeTrip);
        btn_cussignOut=(Button)findViewById(R.id.btn_cusSignOut);
        Intent intent2=getIntent();
        final String cususername=intent2.getStringExtra("key1");
        final String cusemail=intent2.getStringExtra("key2");
        final String custype=intent2.getStringExtra("key3");
        tv_cusname.setText(cususername);
        tv_cusemail.setText(cusemail);
        tv_custype.setText(custype);
        btn_cussetSechdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Customer.this,"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });
        btn_cussetSechdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Customer.this,"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });
        btn_cussignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Customer.this,"Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });
        btn_cussignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_cusname.setText("");
                tv_cusemail.setText("");
                tv_custype.setText("");
                Intent intent3= new Intent(Customer.this,LoginActivity.class);
                startActivity(intent3);
            }
        });

    }
}
