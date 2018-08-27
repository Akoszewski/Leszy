package com.example.user.Leszy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class LocationSenderService extends Service implements LocationListener {
    private static final String LOG_TAG = "LocationSenderService";

    private static final int PERMISSION_FINE_LOCATION = 99;

    private LocationManager mLocationManager = null;
    HandlerThread mLocationHandlerThread = null;
    Looper mLocationHandlerLooper = null;

    private long minIntervalMiliseconds = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("onLocationChanged", location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mLocationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        mLocationHandlerThread = new HandlerThread("locationHandlerThread");
        mLocationHandlerThread.start();
        mLocationHandlerLooper = mLocationHandlerThread.getLooper();
        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minIntervalMiliseconds, 0, this, mLocationHandlerLooper);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        String channelId = createNotificationChannel("location_share_channel");
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Udostępnianie lokalizacji")
                .setTicker("Leszy")
                .setContentText("Udostępnianie lokalizacji jest włączone")
                .setOngoing(true)
                .build();
        startForeground(101, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            mLocationManager.removeUpdates(this);
        } catch (SecurityException e){
            e.printStackTrace();
        }

        mLocationHandlerLooper = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mLocationHandlerThread.quitSafely();
        } else {
            mLocationHandlerThread.quit();
        }

        mLocationHandlerThread = null;
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }

    private final String createNotificationChannel(final String CHANNEL_ID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Udostępnianie lokalizacji";
            String description = "Kanał związany z udostępnianiem lokalizacji (Leszy)";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return CHANNEL_ID;
        } else {
            return "";
        }
    }
}
