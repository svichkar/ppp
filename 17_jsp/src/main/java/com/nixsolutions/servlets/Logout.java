package com.nixsolutions.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        req.setAttribute("userLoggedOut", true);
        RequestDispatcher rd=req.getRequestDispatcher("/index");
        rd.forward(req,resp);
    }
}
