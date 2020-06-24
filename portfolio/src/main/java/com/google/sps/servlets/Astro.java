package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/zodiacsigns")
public class Astro extends HttpServlet {
  private Map<String, Integer> zodiacSign = new HashMap<>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    Gson gson = new Gson();
    String json = gson.toJson(zodiacSign);
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String astrosign = request.getParameter("astrosign");
    int currentVotes = zodiacSign.containsKey(astrosign) ? zodiacSign.get(astrosign) : 0;
    zodiacSign.put(astrosign, currentVotes + 1);

    response.sendRedirect("/index.html");
  }
}