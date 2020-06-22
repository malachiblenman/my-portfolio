// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.ServletException;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
private ArrayList<String> first;
private ArrayList<String> last;
private ArrayList<String> comm;
 @Override
  public void init() throws ServletException { 
    first = new ArrayList<String>();
    last = new ArrayList<String>();
    comm = new ArrayList<String>();
  }
 
 @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String firstname = getParameter(request, "fname", "");
    String lastname= getParameter(request, "lname", "");
    String comment = getParameter(request, "comment", "");
    long timestamp = System.currentTimeMillis();
    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("First Name", firstname);
    taskEntity.setProperty("Last Name", lastname);
    taskEntity.setProperty("Comment", comment);
    taskEntity.setProperty("Timestamp", timestamp);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);
    response.sendRedirect("/index.html");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Task").addSort("Timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    comm = new ArrayList<String>();

    for (Entity entity : results.asIterable()) {
        String comment = (String) entity.getProperty("Comment");
        comm.add(comment);
    }

    String json = new Gson().toJson(comm);
    response.setContentType("application/json");
    response.getWriter().println(json);
  }
  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
   
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}