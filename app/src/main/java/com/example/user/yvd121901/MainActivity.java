package com.example.user.yvd121901;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    MyReceiver receiver;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        it = new Intent(MainActivity.this, MyService.class);
        receiver = new MyReceiver();
    }

    public void clickShow(View v)
    {
        Intent it = new Intent(MainActivity.this, Main2Activity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
                    123, it, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        builder.setContentTitle("這是標題")
                .setContentText("這裡是內容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi);
        Notification notification = builder.build();
        nm.notify(1, notification);
    }

    public void clickService(View v)
    {
        startService(it);
    }

    public void clickStop(View v)
    {
        stopService(it);
    }

    public void clickOn(View v)
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, intentFilter);
    }

    public void clickOff(View v)
    {
        unregisterReceiver(receiver);
    }
}
