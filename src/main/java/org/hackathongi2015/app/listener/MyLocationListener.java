package org.hackathongi2015.app.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by pgarriga on 28/3/15.
 */
public class MyLocationListener implements LocationListener {

    public void onLocationChanged(Location loc) {
        System.out.println( "Location changed: Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude());
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
