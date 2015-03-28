package org.hackathongi2015;

import android.app.Application;
import android.content.res.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hackathongi2015.app.api.JobsService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class App extends Application {

  JobsService mREST;

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }

  @Override
  public void onCreate() {
    super.onCreate();

    Gson gson = new GsonBuilder()
        .setDateFormat("dd-MM-yyy HH:mm")
        .create();

    RestAdapter restAdapter = new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setEndpoint("http://private-98213-hackathongi2015.apiary-mock.com")
        .setConverter(new GsonConverter(gson))
        .build();

    mREST = restAdapter.create(JobsService.class);
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }

  public JobsService getREST() {
    return mREST;
  }
}