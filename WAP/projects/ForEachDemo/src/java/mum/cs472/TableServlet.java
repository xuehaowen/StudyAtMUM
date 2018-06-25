/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toby_
 */
public class TableServlet extends HttpServlet {

    static final long serialVersionUID = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Student[] table = new Student[]{
            new Student("bob", 23),
            new Student("jill", 33),
            new Student("kim", 18)
        };
        request.setAttribute("students", table);
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("table.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("table.jsp");
        dispatcher.forward(request, response);
    }

}
