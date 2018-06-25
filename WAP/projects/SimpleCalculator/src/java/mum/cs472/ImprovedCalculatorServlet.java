/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toby_
 */
public class ImprovedCalculatorServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String plus1str = request.getParameter("plus1");
        String plus2str = request.getParameter("plus2");
        String mutilply1str = request.getParameter("mutilply1");
        String mutilply2str = request.getParameter("mutilply2");
        String re1 = null;
        String re2 = null;
        if (plus1str.length() != 0 && plus2str.length() != 0) {
            int plus1 = Integer.valueOf(plus1str);
            int plus2 = Integer.valueOf(plus2str);
            re1 = "" + (plus1 + plus2);
        }
        if (mutilply1str.length() != 0 && mutilply2str.length() != 0) {
            int mutilply1 = Integer.valueOf(mutilply1str);
            int mutilply2 = Integer.valueOf(mutilply2str);
            re2 = "" + mutilply1 * mutilply2;
        }
        request.setAttribute("plus1", plus1str);
        request.setAttribute("plus2", plus2str);
        request.setAttribute("re1", re1);
        request.setAttribute("mutilply1", mutilply1str);
        request.setAttribute("mutilply2", mutilply2str);
        request.setAttribute("re2", re2);
        System.out.println("re1=" + re1);
        System.out.println("re2=" + re2);
        request.getRequestDispatcher("/improvedCal.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
