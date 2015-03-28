package org.hackathongi2015.app.listener;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by pgarriga on 28/3/15.
 */
public class MyLocationListener implements LocationListener {
    Context mContext;

    public MyLocationListener(Context mContext) {
        this.mContext = mContext;
    }

    public void onLocationChanged(Location loc) {
        double lat = loc.getLatitude();
        double lng = loc.getLongitude();
        String city  = "";

        System.out.println("Location changed: Lat: " + lat + " Lng: " + lng);

        Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                city =  addresses.get(0).getLocality();
                System.out.println("City: " + city);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
