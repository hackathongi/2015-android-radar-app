package org.hackathongi2015.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.udg.pds.simpleapp_android.R;


public class JobListFragment extends Fragment {
  public static final String ARG_OBJECT = "object";

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    // The last two arguments ensure LayoutParams are inflated
    // properly.
    View rootView = inflater.inflate(
        R.layout.job_list, container, false);
    return rootView;
  }
}
