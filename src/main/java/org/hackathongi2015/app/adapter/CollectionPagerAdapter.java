package org.hackathongi2015.app.adapter;
 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import org.hackathongi2015.app.fragment.JobListFragment;
import org.hackathongi2015.app.fragment.MapFragment;
import org.hackathongi2015.app.util.Global;
import org.hackathongi2015.app.util.JSON;
import org.udg.pds.simpleapp_android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i%2) {
            case 0: {
                fragment = new MapFragment();;
                break;
            }
            case 1: {
                fragment = new JobListFragment();;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}

