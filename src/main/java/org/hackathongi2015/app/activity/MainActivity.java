package org.hackathongi2015.app.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import org.hackathongi2015.app.adapter.CollectionPagerAdapter;
import org.udg.pds.simpleapp_android.R;

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
        mDemoCollectionPagerAdapter =
            new CollectionPagerAdapter(
                getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

    }
}