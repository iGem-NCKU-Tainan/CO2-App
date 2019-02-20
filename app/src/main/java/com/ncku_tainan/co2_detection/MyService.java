package com.ncku_tainan.co2_detection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class MyService extends Service implements ChildEventListener {

    FirebaseDatabase fireDB;
    DatabaseReference month;
    SimpleDateFormat sdf = new SimpleDateFormat("MM");
    String sdfmonth = sdf.format(new java.util.Date());
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
    String hour = sdf2.format(new java.util.Date());
    SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
    String date = sdf3.format(new java.util.Date());
    SimpleDateFormat sdf4 = new SimpleDateFormat("ss");
    String second = sdf4.format(new java.util.Date());
    SimpleDateFormat sdf5 = new SimpleDateFormat("mm");
    String minute = sdf5.format(new java.util.Date());
    boolean inNoticed;
    boolean inNoticed2;
    boolean inNoticed3;
    String TAG = "MyService";

    @Override
    public void onCreate() {
        fireDB = FirebaseDatabase.getInstance();
        month = fireDB.getReference(sdfmonth);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) { }
    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) { }
    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
    @Override
    public void onCancelled(DatabaseError databaseError) { }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        final Intent notificationIntent = new Intent(this, pH_value.class);
        final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        month.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String pHvalue;
                String concentration;
                String temperature;
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("pH").getValue() != null)) {
                    pHvalue = dataSnapshot.child(hour + ":" + minute + ":" + second).child("pH").getValue() + "";
                    if (Float.parseFloat(pHvalue) > 7.5 ||  Float.parseFloat(pHvalue) < 6) {
                        inNoticed = true;
                        Log.d(TAG, "onChildAdded() returned pH warning: " + Float.parseFloat(pHvalue));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("pH Value Warning")
                                .setContentText("The value of pH is abnormal in "+ hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(1, notification);
                        //manager.cancel(1);
                    } else {
                        inNoticed = false;
                        Log.d(TAG, "onChildAdded() returned pH no warning: " + Float.parseFloat(pHvalue));
                        manager.cancel(1);
                    }
                }
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("concentration").getValue() != null)) {
                    concentration = dataSnapshot.child(hour + ":" + minute + ":" + second).child("concentration").getValue() + "";
                    if (Float.parseFloat(concentration) > 5) {
                        inNoticed = true;
                        Log.d(TAG, "onChildAdded() returned concentration warning: " + Float.parseFloat(concentration));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("CO2 Concentration Warning")
                                .setContentText("The concentration of CO2 is abnormal in "+ hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(2, notification);
                        //manager.cancel(2);
                    } else {
                        inNoticed = false;
                        Log.d(TAG, "onChildAdded() returned concentration no warning: " + Float.parseFloat(concentration));
                        manager.cancel(2);
                    }
                } else {
                    Log.d(TAG, "onChildAdded() returned no data: " + inNoticed);
                }
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("temperature").getValue() != null)) {
                    temperature = dataSnapshot.child(hour + ":" + minute + ":" + second).child("temperature").getValue() + "";
                    if (Float.parseFloat(temperature) > 40 || Float.parseFloat(temperature) < 22) {
                        inNoticed2 = true;
                        Log.d(TAG, "onChildAdded() returned temperature warning: " + Float.parseFloat(temperature));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("Temperature Warning")
                                .setContentText("The temperature is abnormal in "+ hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(3, notification);
                        //manager.cancel(2);
                    } else {
                        inNoticed2 = false;
                        Log.d(TAG, "onChildAdded() returned temperature no warning: " + Float.parseFloat(temperature));
                        manager.cancel(3);
                    }
                } else {
                    Log.d(TAG, "onChildAdded() returned no data: " + inNoticed2);
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String pHvalue;
                String concentration;
                String temperature;
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("pH").getValue() != null)) {
                    pHvalue = dataSnapshot.child(hour + ":" + minute + ":" + second).child("pH").getValue() + "";
                    if(Float.parseFloat(pHvalue) > 7.5 || Float.parseFloat(pHvalue) < 6) {
                        inNoticed3 = true;
                        Log.d(TAG, "onChildChanged() returned pH warning: " + Float.parseFloat(pHvalue));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("pH Value Warning")
                                .setContentText("The value of pH is abnormal in " + hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(1, notification);
                        //manager.cancel(1);
                    }
                    else {
                        inNoticed3 = false;
                        Log.d(TAG, "onChildChanged() returned pH no warning: " + Float.parseFloat(pHvalue));
                        manager.cancel(1);
                    }
                }
                else {
                    Log.d(TAG, "onChildChanged() returned no data: " + inNoticed3);
                }
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("concentration").getValue() != null)) {
                    concentration = dataSnapshot.child(hour + ":" + minute + ":" + second).child("concentration").getValue() + "";
                    if(Float.parseFloat(concentration) > 5) {
                        inNoticed2 = true;
                        Log.d(TAG, "onChildChanged() returned concentration warning: " + Float.parseFloat(concentration));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("Concentration Warning")
                                .setContentText("The value of concentration is abnormal in " + hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(2, notification);
                        //manager.cancel(1);
                    }
                    else {
                        inNoticed2 = false;
                        Log.d(TAG, "onChildChanged() returned concentration no warning: " + Float.parseFloat(concentration));
                        manager.cancel(2);
                    }
                }
                else {
                    Log.d(TAG, "onChildChanged() returned no data: " + inNoticed2);
                }
                if ((dataSnapshot.getKey().equals(date)) && (dataSnapshot.child(hour + ":" + minute + ":" + second).child("temperature").getValue() != null)) {
                    temperature = dataSnapshot.child(hour + ":" + minute + ":" + second).child("temperature").getValue() + "";
                    if(Float.parseFloat(temperature) > 40 || Float.parseFloat(temperature) < 22) {
                        inNoticed3 = true;
                        Log.d(TAG, "onChildChanged() returned temperature warning: " + Float.parseFloat(temperature));
                        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        builder.setDefaults(Notification.DEFAULT_ALL);
                        builder.setSmallIcon(R.drawable.wiki_logo)
                                .setWhen(System.currentTimeMillis())
                                .setContentTitle("Temperature Warning")
                                .setContentText("The value of temperature is abnormal in " + hour + ":" + minute + ":" + second + ".")
                                .setContentIntent(contentIntent);

                        Notification notification = builder.build();
                        // The notification will disappear automatically when user clicks it
                        notification.flags |= Notification.FLAG_AUTO_CANCEL;
                        manager.notify(3, notification);
                        //manager.cancel(3);
                    }
                    else {
                        inNoticed3 = false;
                        Log.d(TAG, "onChildChanged() returned temperature no warning: " + Float.parseFloat(temperature));
                        manager.cancel(3);
                    }
                }
                else {
                    Log.d(TAG, "onChildChanged() returned no data: " + inNoticed3);
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        //stopSelf();  // 停止Service
        Log.d(TAG, "addChildEventListener() returned out ");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("HelloService", "onDestroy");
    }
}
