package org.hackathongi2015.app.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.hackathongi2015.App;
import org.hackathongi2015.R;
import org.hackathongi2015.app.util.Global;
import org.hackathongi2015.app.util.JSON;
import org.hackathongi2015.app.util.JSON.Job;
import retrofit.RetrofitError;

import java.util.ArrayList;
import java.util.List;

public class JobListFragment extends ListFragment {

  ArrayList<Job> mJobList = new ArrayList<Job>();
  ArrayAdapter<Job> mAdapter;
  ViewGroup mContainer;

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    mContainer = container;
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    // Since onActivityCreated can be called multiple times in the Fragment lifecycle, we only initialize the
    // adapter if it is null (the first time)
    if (mAdapter == null) {
      mAdapter = new JobsAdapter(getActivity(), mContainer.getId());
      setListAdapter(mAdapter);
    }

    // getListView().setOnItemClickListener(this);

  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  @Override
  public void onResume() {
    super.onResume();

    new AsyncTask<Void, Void, List<Job>>() {

      private Exception exception;

      @Override
      protected List<JSON.Job> doInBackground(Void ... params) {
        try {
          return ((App) getActivity().getApplicationContext()).getREST().listJobs();
        } catch (RetrofitError e) {
          this.exception = e;
          return null;
        }
      }

      @Override
      protected void onPostExecute(List<JSON.Job> jobs) {
        mAdapter.addAll(jobs);
      }
    }.execute();





  }
}

class JobsAdapter extends ArrayAdapter<Job> {

  private LayoutInflater mInflater = null;

  //Initialize adapter
  public JobsAdapter(Context context, int mResource) {
    super(context, mResource);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  // This is the main function
  // It generates a View for each Task that we want to show in the list
  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    LinearLayout alertView;
    //Get the current alert object
    JSON.Job job = getItem(position);

    View vi = convertView;
    //Inflate the view if there is no other View (convertView) that the system gives us to "recycle"
    if(convertView==null)
    {
      vi = mInflater.inflate(R.layout.job_item, null);
    }
    //Get the text boxes
    TextView title =(TextView)vi.findViewById(R.id.title);

    // Assign the appropriate data from our task object above
    title.setText(job.title);

    return vi;
  }
}
