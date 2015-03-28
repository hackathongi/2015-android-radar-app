package org.hackathongi2015.app.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by imartin on 22/02/14.
 */
public class JSON {

  public static class Job {
    public Long id;
    public String title;
    public String description;
    public String start_date;
    public String end_date;
    public String city;
    public Float latitude;
    public Float longitude;
    public String picture_url;
    public JSON.Owner owner;
    public String job_url;
  }

  public static class Error {
    String msg;
  }

  public static class JobList {
    public List<Job> tasks;

    public List<Job> getJob() {
      return tasks;
    }
  }

  public static class Owner {
    public Long id;
    public String name;
    public String email;
    public String token;
    Integer role;
  }
/*
  public static String toJSON(Object obj) throws IOException {
    StringWriter sw = new StringWriter();
    new ObjectMapper().writeValue(sw, obj);
    return sw.toString();
  }
  */
}
