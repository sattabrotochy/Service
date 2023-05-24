package com.example.appservicejava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    Button start,stop,button3;
    EditText editText;
    AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.button);
        stop=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        editText=findViewById(R.id.editTextTextPersonName);

        manager= (AlarmManager) getSystemService(ALARM_SERVICE);



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int time=Integer.parseInt(editText.getText().toString().trim());

                long tiggertime= System.currentTimeMillis()+(time* 1000L);
                Intent intent=new Intent(MainActivity.this, MyReceiver.class);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,101,intent,0);
                manager.set(AlarmManager.RTC_WAKEUP,tiggertime,pendingIntent);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this,MusicService.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,MusicService.class));
            }
        });

    }
}