package com.nixsolutions.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	out.println("<!DOCTYPE html>\n"+
				"<html>\n"+
				"<head><title>My first ever servlet</title></head>\n"+
				"<body bgcolor=\"#fdf5e6\">"+
				"<h1>It is so cool</h1>"+
				"<p>Here is my servlet. It is alive!</p>"+
				"</body></html>");
}
}
