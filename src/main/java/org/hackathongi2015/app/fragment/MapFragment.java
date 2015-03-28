package org.hackathongi2015.app.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.hackathongi2015.App;
import org.hackathongi2015.app.activity.JobDescription;
import org.hackathongi2015.app.util.Dialog;
import org.hackathongi2015.app.util.JSON;
import retrofit.RetrofitError;

import java.util.HashMap;
import java.util.List;

public class MapFragment extends com.google.android.gms.maps.SupportMapFragment {
  public static final String ARG_OBJECT = "object";

  HashMap<Integer, JSON.Job> mJobsMap = new HashMap<Integer, JSON.Job>();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    final View vi = super.onCreateView(inflater, container, savedInstanceState);

    new AsyncTask<Void, Void, List<JSON.Job>>() {
      private Exception exception = null;

      @Override
      protected List<JSON.Job> doInBackground(Void... params) {
        try {
          return ((App) getActivity().getApplicationContext()).getREST().listJobs();
        } catch (RetrofitError e) {
          this.exception = e;
          return null;
        }
      }

      @Override
      protected void onPostExecute(List<JSON.Job> jobs) {
        if (jobs != null) {
          for (JSON.Job job : jobs) {
            if (job!=null) {
              Marker marker = getMap().addMarker(new MarkerOptions().position(new LatLng(job.latitude, job.longitude)));
              mJobsMap.put(marker.hashCode(), job);
            }
          }
        } else {
          Dialog.onError("Error in REST call", getActivity(), this.exception.getMessage(), null);
        }
      }
    }.execute();

    getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
      @Override
      public boolean onMarkerClick(Marker marker) {

        JSON.Job job = mJobsMap.get(marker.hashCode());

        Intent intent = new Intent(getActivity(), JobDescription.class);
        intent.putExtra("JOB_TITLE", job.title);
        intent.putExtra("JOB_DESC", job.description);
        intent.putExtra("JOB_IMAGE_URL", job.picture_url);
        intent.putExtra("JOB_CITY", job.city);
        intent.putExtra("JOB_DATE", job.end_date);
        intent.putExtra("JOB_JOB_URL", "https://landing.wallyjobs.com/" + job.id);
        intent.putExtra("JOB_NAME", job.owner.name);

        startActivity(intent);

        return true;
      }
    });

    return vi;
  }


}
