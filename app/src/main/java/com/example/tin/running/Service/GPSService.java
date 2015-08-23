package com.example.tin.running.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.example.tin.running.R;
import com.google.android.gms.maps.model.LatLng;

public class GPSService extends Service {

    public static final String TAG = "GPSServiceTAG";

    // variables a devolver
    private double maxSpeed = 0.0;
    private double distance = 0.0;
    private double currentSpeed = 0.0;

    // variables auxiliares

    private LatLng coordinate ;

    double currentLon = 0;
    double currentLat = 0;
    double lastLon = 0;
    double lastLat = 0;

    private int count = 0 ;
    //variables gps

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    public void onCreate() {
        startGPS();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startGPS();
        return 1;
    }

    public void startGPS() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                currentSpeed = location.getSpeed();
                if (maxSpeed < currentSpeed)
                    maxSpeed = currentSpeed;

                //start location manager
                //LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

                //Get last location
                //Location loc = lm.getLastKnownLocation(lm.GPS_PROVIDER);
                if (count == 0) {
                    lastLat = location.getLatitude();
                    lastLon = location.getLongitude();
                    currentLat = location.getLatitude();
                    currentLon = location.getLongitude();
                    coordinate = new LatLng(currentLat, currentLon);
                }
                else
                {
                    lastLat = currentLat ;
                    lastLon = currentLon ;
                    currentLat = location.getLatitude();
                    currentLon = location.getLongitude();
                    coordinate = new LatLng(currentLat, currentLon);
                }
                count++;
                

                Location locationA = new Location("point A");
                locationA.setLatitude(lastLat);
                locationA.setLongitude(lastLon);

                Location locationB = new Location("point B");
                locationB.setLatitude(currentLat);
                locationB.setLongitude(currentLon);

                double distanceMeters = locationA.distanceTo(locationB);

                distance = distance + (distanceMeters);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mGPSBinder;
    }

    public double getDistance() {
        return distance;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public LatLng getCoordinate (){ return coordinate;}

    private final IBinder mGPSBinder = new GPSBinder();


public class GPSBinder extends Binder {
    public GPSService getService() {
        return GPSService.this;
    }
}

}