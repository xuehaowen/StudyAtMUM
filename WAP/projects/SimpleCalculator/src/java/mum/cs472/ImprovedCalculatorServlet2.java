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
public class ImprovedCalculatorServlet2 extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>SimpleCalculatorResult</title></head><body>");
        out.print("<form action=\"ImprovedCalculator\" method=\"post\" >");
        out.print("<input name=\"plus1\" type=\"text\" value=\""+plus1str+"\" />+");
        out.print("<input name=\"plus2\" type=\"text\" value=\""+plus2str+"\" />=");
        out.print("<input type=\"text\" value=\""+re1+"\" /><br/>");
        out.print("<input name=\"mutilply1\" type=\"text\" value=\""+mutilply1str+"\" />*");
        out.print("<input name=\"mutilply2\" type=\"text\" value=\""+mutilply2str+"\" />=");
        out.print("<input type=\"text\" value=\""+re2+"\" /><br/>");
        out.print("<input type=\"submit\" value=\"submit\" />");
        out.print("</form>");
        out.print("</body></html>");
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
