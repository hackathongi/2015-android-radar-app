package org.hackathongi2015.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.hackathongi2015.R;
import org.hackathongi2015.app.util.Global;

import java.util.Date;

/**
 * Created by imartin on 28/03/15.
 */
public class JobDescription extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // setTheme(SampleList.THEME);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.job_desc);

    Bundle extras = getIntent().getExtras();
    if (extras == null) {
      this.finish();
      return;
    }

    View vi = this.getWindow().getDecorView();
    TextView title =(TextView)vi.findViewById(R.id.title);
    ImageView iview = (ImageView)vi.findViewById(R.id.imageView);
    TextView description =(TextView)vi.findViewById(R.id.description);
    TextView city =(TextView)vi.findViewById(R.id.city);
    TextView date =(TextView)vi.findViewById(R.id.date);

    Date d;
    try {
      d = Global.FULL_TIME_DATE_FORMAT.parse(extras.getString("JOB_DATE"));
    } catch (Exception e) {
      d = new Date();
    }

    // Assign the appropriate data from our task object above
    title.setText(extras.getString("JOB_TITLE"));
    description.setText(extras.getString("JOB_DESC"));
    ImageLoader.getInstance().displayImage(extras.getString("JOB_IMAGE_URL"), iview);
    city.setText(extras.getString("JOB_CITY"));
    date.setText(Global.DATE_ONLY_FORMAT.format(d));
  }

}
