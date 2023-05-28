package com.example.appservicejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImplicitActivity extends AppCompatActivity {

    Button btnDail,btnMessage,btnEmail,btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);


        btnDail=findViewById(R.id.btnDial);
        btnMessage=findViewById(R.id.btnMessage);
        btnEmail=findViewById(R.id.btnEmail);
        btnShare=findViewById(R.id.btnShare);


        btnDail.setOnClickListener(view -> {

            Intent intentDial=new Intent(Intent.ACTION_DIAL);
            intentDial.setData(Uri.parse("tel: +8801705212104"));
            startActivity(intentDial);

        });


        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMessage=new Intent(Intent.ACTION_SEND);
                intentMessage.setData(Uri.parse("smsto:"+Uri.encode("8801705212104")));
                startActivity(intentMessage);
            }
        });
    }
}