/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472.model;

/**
 *
 * @author toby_
 */
public class Quiz {

    private final static String[] questions = {
        "3,1,4,1,5",//pi
        "1,1,2,3,5",//fibonacci
        "1,4,9,16,25",//square
        "2,3,5,7,11",//primes
        "1,2,4,8,16"//power of 2
    };
    private final static int[] answers = {9, 8, 36, 13, 32};
    private int score = 0;
    private int curQue = 0;

    public String getOutput() {
        StringBuilder sb = new StringBuilder("<html><head><title>Quiz</title></head><body>");
        sb.append("<h1>The Number Quiz</h1>");
        sb.append("<p>Your current score is ").append(score).append(".</p>");
        if (curQue > 4) {
            sb.append("You have completed the Number Quiz,with a score of ").append(score).append(" out of ").append(5);
        } else {
            sb.append("<p>Guess the next number in the sequence.</p>");
            sb.append("<p>").append(questions[curQue]).append("</p>");
            sb.append("<form action='Quiz.do' method='post'>");
            sb.append("Your answer:").append("<input type='number' name='answer'/><br/>");
            sb.append("<input type='submit' value='Submit'/></form>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    public void checkAns(int answer) {
        if (curQue < 5) {
            if (answer == answers[curQue]) {
                score++;
            }
            curQue++;
        }
    }
}
