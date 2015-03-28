package org.hackathongi2015.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import org.hackathongi2015.R;
import org.hackathongi2015.app.adapter.CollectionPagerAdapter;

public class MainActivity extends SherlockFragmentActivity {

  CollectionPagerAdapter mDemoCollectionPagerAdapter;
  ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // setTheme(SampleList.THEME);
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


  }
}