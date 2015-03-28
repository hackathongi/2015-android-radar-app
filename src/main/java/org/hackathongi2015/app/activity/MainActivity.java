package org.hackathongi2015.app.activity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.view.MenuInflater;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import org.hackathongi2015.R;
import org.hackathongi2015.app.adapter.CollectionPagerAdapter;
import org.hackathongi2015.app.listener.MyLocationListener;

public class MainActivity extends SherlockFragmentActivity {

    CollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_main);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        ActionBar abar = this.getSupportActionBar();
        abar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add the two tabs
        abar.addTab(abar.newTab().setText("Job List").setTabListener(tabListener));
        abar.addTab(abar.newTab().setText("Map").setTabListener(tabListener));
        mViewPager.setOnPageChangeListener(

        new ViewPager.SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            // When swiping between pages, select the
            // corresponding tab.
            getSupportActionBar().setSelectedNavigationItem(position);
          }
        });


        // Get location
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener(MainActivity.this.getApplicationContext());
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);


    }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    getSupportMenuInflater().inflate(R.menu.main_activity_actions, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.action_search:
        return true;
      case R.id.action_website:
        Intent intent = new Intent(MainActivity.this, WallyWebView.class);
        intent.putExtra("JOB_URL", "https://www.wallyjobs.com");
        startActivity(intent);
        return true;
      default:
        return false;
    }
  }
}