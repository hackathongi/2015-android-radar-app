package org.hackathongi2015.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.hackathongi2015.R;

public class MapFragment extends Fragment {
  public static final String ARG_OBJECT = "object";

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    // The last two arguments ensure LayoutParams are inflated
    // properly.
    View rootView = inflater.inflate(
        R.layout.map, container, false);
    return rootView;
  }
}
