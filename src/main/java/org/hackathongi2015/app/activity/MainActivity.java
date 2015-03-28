package org.hackathongi2015.app.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import org.hackathongi2015.R;
import org.hackathongi2015.app.adapter.CollectionPagerAdapter;
import org.hackathongi2015.app.listener.MyLocationListener;

public class MainActivity extends SherlockFragmentActivity {

    CollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setTheme(SampleList.THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new CollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        // Get location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener(MainActivity.this.getApplicationContext());
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    }
}