package com.example.appservicejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String CHANEL_ID="My chanel";
    private static final int NOTIFICATION_ID=1;

    Button start, stop, button3;
    EditText editText;
    AlarmManager manager;
    NotificationManager notificationManager;
    Notification notification;
    Drawable drawable;
    BitmapDrawable bitmapDrawable;
    Bitmap bitmap;
    PendingIntent pendingIntent;

    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.button);
        stop = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editText = findViewById(R.id.editTextTextPersonName);

        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.image, null);
        bitmapDrawable = (BitmapDrawable) drawable;
        assert bitmapDrawable != null;
        bitmap = bitmapDrawable.getBitmap();

        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

       Intent intent=new Intent(getApplicationContext(),MainActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent= PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_MUTABLE);
        }
        Notification.BigPictureStyle bigPictureStyle=new Notification.BigPictureStyle()
                .bigPicture(bitmap);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.image)
                    .setContentText("new Message")
                    .setContentTitle("new notification")
                    .setContentIntent(pendingIntent)
                    .setChannelId(CHANEL_ID)
                    .setStyle(bigPictureStyle)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANEL_ID,"MY CHANEL",NotificationManager.IMPORTANCE_HIGH));

        }else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.image)
                    .setContentText("new Message")
                    .setContentTitle("new notification")
                    .setContentIntent(pendingIntent)
                    .setStyle(bigPictureStyle)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID,notification);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int time = Integer.parseInt(editText.getText().toString().trim());

                long tiggertime = System.currentTimeMillis() + (time * 1000L);
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 101, intent, 0);
                manager.set(AlarmManager.RTC_WAKEUP, tiggertime, pendingIntent);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MusicService.class));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MusicService.class));
            }
        });

    }
}