/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        Quiz quiz = (Quiz) s.getAttribute("quiz");
        if (quiz == null) {
            quiz = new Quiz();
            s.setAttribute("quiz", quiz);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
        //Age validate
        String ageStr = request.getParameter("age");
        String ageHint = null;
        boolean isAgeValidated = false;
        String page = "/index.jsp";
        if (ageStr == null) {
            ageHint = "Age is required.";
        } else {
            try {
                int age = Integer.valueOf(ageStr);
                if (age < 4 || age > 100) {
                    ageHint = "Age must be between 4 and 100.";
                } else {
                    isAgeValidated = true;
                    request.setAttribute("age", age);
                }
            } catch (NumberFormatException e) {
                ageHint = "Age must be numeric.";
            }
        }
        request.setAttribute("ageHint", ageHint);
        HttpSession s = request.getSession();
        if (request.getParameter("reStart") != null) {
            request.removeAttribute("ageHint");
            s.setAttribute("quiz", new Quiz());
        } else {
            //Quiz check
            Quiz quiz = (Quiz) s.getAttribute("quiz");
            if (quiz == null) {
                quiz = new Quiz();
                s.setAttribute("quiz", quiz);
            } else {
                if (isAgeValidated) {
                    String answerStr = request.getParameter("answer");
                    int anwser = Integer.MIN_VALUE;
                    try {
                        anwser = Integer.valueOf(answerStr);
                    } catch (NumberFormatException e) {
                    }
                    quiz.checkAns(anwser);
                    if (quiz.isEnd()) {
                        page = "/gg.jsp";
                    }
                }
            }
        }
        request.getRequestDispatcher(page).forward(request, response);
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
