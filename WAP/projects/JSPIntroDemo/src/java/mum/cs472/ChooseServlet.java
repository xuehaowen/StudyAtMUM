/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toby_
 */
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mum.cs472.model.RadioState;

public class ChooseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher("Choose.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String choice1 = request.getParameter("radioJSPCool");
        String choice2 = request.getParameter("JSFwayCool");
        String choice3 = request.getParameter("moonCheese");
        if (choice1 != null) {
            System.out.println("choice= " + choice1);
        } else {
            System.out.println("No choice made");
        }
        String radioJSPCool = "";
        String JSFwayCool = "";
        if (choice1 != null) {
            if (choice1.equals("1")) {
                radioJSPCool = "radioJSPCool_1";
            } else {
                radioJSPCool = "radioJSPCool_2";
            }
            request.setAttribute(radioJSPCool, "checked");
        }
        if (choice2 != null) {
            if (choice2.equals("1")) {
                JSFwayCool = "JSFwayCool_1";
            } else {
                JSFwayCool = "JSFwayCool_2";
            }
            request.setAttribute(JSFwayCool, "checked");
        }
        if (choice3 != null) {
            RadioState rs = new RadioState();
            rs.check(choice3);
            request.setAttribute("moonCheese", rs);
        }
        RequestDispatcher dispatch = request.getRequestDispatcher("Choose.jsp");
        dispatch.forward(request, response);
    }
}
