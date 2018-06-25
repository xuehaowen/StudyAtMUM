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
import javax.servlet.http.HttpSession;
import mum.cs472.model.Quiz;

/**
 *
 * @author toby_
 */
public class QuizServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        Quiz quiz = (Quiz) s.getAttribute("quiz");
        if (quiz == null) {
            quiz = new Quiz();
            s.setAttribute("quiz", quiz);
        }
        PrintWriter out = response.getWriter();
        out.println(quiz.getOutput());
    }

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
        HttpSession s = request.getSession();
        Quiz quiz = (Quiz) s.getAttribute("quiz");
        if (quiz == null) {
            quiz = new Quiz();
            s.setAttribute("quiz", quiz);
        } else {
            String answerStr = request.getParameter("answer");
            int anwser = Integer.MIN_VALUE;
            try {
                anwser = Integer.valueOf(answerStr);
            } catch (NumberFormatException e) {
            }
            quiz.checkAns(anwser);
        }
        PrintWriter out = response.getWriter();
        out.println(quiz.getOutput());
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
