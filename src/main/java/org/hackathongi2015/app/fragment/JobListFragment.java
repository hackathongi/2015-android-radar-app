package org.hackathongi2015.app.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.hackathongi2015.App;
import org.hackathongi2015.R;
import org.hackathongi2015.app.activity.JobDescription;
import org.hackathongi2015.app.util.Global;
import org.hackathongi2015.app.util.JSON;
import org.hackathongi2015.app.util.JSON.Job;
import retrofit.RetrofitError;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JobListFragment extends ListFragment  {

  ArrayList<Job> mJobList = new ArrayList<Job>();
  HashMap<Long, Boolean> mJobMap = new HashMap<Long, Boolean>();
  ArrayAdapter<Job> mAdapter;
  ViewGroup mContainer;

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);

    JSON.Job job = mAdapter.getItem(position);

    Intent intent = new Intent(getActivity(), JobDescription.class);
    intent.putExtra("JOB_TITLE", job.title);
    intent.putExtra("JOB_DESC", job.description);
    intent.putExtra("JOB_IMAGE_URL", job.picture_url);
    intent.putExtra("JOB_CITY", job.city);
    intent.putExtra("JOB_DATE", job.end_date);
    intent.putExtra("JOB_JOB_URL", job.job_url);

    startActivity(intent);
  }

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
        for (JSON.Job job : jobs) {
          if (!mJobMap.containsKey(job.id)) {
            mJobMap.put(job.id, true);
            mAdapter.add(job);
          }
        }
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
    ImageView iview = (ImageView)vi.findViewById(R.id.imageView);
    TextView description =(TextView)vi.findViewById(R.id.description);
    TextView city =(TextView)vi.findViewById(R.id.city);
    TextView date =(TextView)vi.findViewById(R.id.date);

    Date d;
    try {
      d = Global.FULL_TIME_DATE_FORMAT.parse(job.end_date);
    } catch (Exception e) {
      d = new Date();
    }

    // Assign the appropriate data from our task object above
    title.setText(job.title);
    description.setText(job.description);
    ImageLoader.getInstance().displayImage(job.picture_url, iview);
    city.setText(job.city);
    date.setText(Global.DATE_ONLY_FORMAT.format(d));
    return vi;
  }
}
