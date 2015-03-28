package org.hackathongi2015.app.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by imartin on 22/02/14.
 */
public class JSON {

    public static class Task {
        public long id;
        public String text;
        public Date dateLimit;
        public Date dateCreated;
        public boolean completed;

    }

    public static class Error {
        public String msg;

        public Error() {}

        public Error(String s) {
            msg = s;
        }
    }

    public static class User {
        public long id;
        public String username;
        public String email;
    }

    public static class TaskList {
        public List<Task> tasks;

        public List<Task> getTasks() {
            return tasks;
        }
    }
/*
  public static String toJSON(Object obj) throws IOException {
    StringWriter sw = new StringWriter();
    new ObjectMapper().writeValue(sw, obj);
    return sw.toString();
  }
  */
}
