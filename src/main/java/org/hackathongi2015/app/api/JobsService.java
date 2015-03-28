package org.hackathongi2015.app.api;

import org.hackathongi2015.app.util.JSON;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

/**
 * Created by imartin on 28/03/15.
 */
public interface JobsService {
    @GET("/api/jobs")
    List<JSON.Job> listJobs();
}
