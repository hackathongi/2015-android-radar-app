package org.hackathongi2015.app.adapter;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import org.hackathongi2015.app.fragment.JobListFragment;
import org.hackathongi2015.app.fragment.MapFragment;


public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i%2) {
            case 0: {
                fragment = new JobListFragment();;
                break;
            }
            case 1: {
                fragment = new MapFragment();;
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

