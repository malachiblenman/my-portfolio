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

private ArrayList<String> messages = new ArrayList<String>();
 @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String firstname = getParameter(request, "fname", "");
    String lastname= getParameter(request, "lname", "");
    String comment = getParameter(request, "comment", "");
  }

  @Override
  public void init() throws ServletException { //Affirmations
    messages.add("fname");
    messages.add("lname");
    messages.add("comment");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Gson gson = new Gson();
    //String json = gson.toJson(messages);
      String json = convertToJson(messages);
    response.setContentType("text/html;");
    response.getWriter().println(json);
  }
  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */

     private String convertToJson(ArrayList<String> messages) {
    String json = "{";
    json += "\"firstname\": ";
    json += "\"" + messages.get(0) + "\"";
    json += ", ";
    json += "\"lastname\": ";
    json += "\"" + messages.get(1) + "\"";
    json += ", ";
    json += "\"location\": ";
    json += "\"" + messages.get(2) + "\"";
    json += "}";
     return json;
     }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}