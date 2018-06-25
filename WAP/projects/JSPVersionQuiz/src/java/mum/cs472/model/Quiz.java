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

    private final static String[] QUESTIONS = {
        "3,1,4,1,5",//pi
        "1,1,2,3,5",//fibonacci
        "1,4,9,16,25",//square
        "2,3,5,7,11",//primes
        "1,2,4,8,16"//power of 2
    };
    private final static int[] ANSWERS = {9, 8, 36, 13, 32};
    private final static String[] HINTS = {"PI", "Fibonacci", "Square", "Primes", "Power of 2"};
    private int score = 0;
    private int curQue = 0;
    private int attempt = 0;
    private String msg = "";
//    private String grade;
//    private int answer;
//    private String question;
//    private String hint = "";
    
    public boolean isEnd(){
        if (curQue == 5) {
            return true;
        }
        return false;
    }

    public String getGrade() {
        String grade = "";
        if (curQue == 5) {
            if (score > 45) {
                grade = "A";
            } else if (score > 35) {
                grade = "B";
            } else if (score > 25) {
                grade = "C";
            } else {
                grade = "NC";
            }
        }
        return grade;
    }

    public void checkAns(int answer) {
        if (curQue < 5) {
            if (attempt == 3) {
                curQue++;
                attempt = 0;
                msg = "";
                return;
            }
            if (answer == ANSWERS[curQue]) {
                switch (attempt) {
                    case 0:
                        score += 10;
                        break;
                    case 1:
                        score += 5;
                        break;
                    case 2:
                        score += 2;
                        break;
                    default:
                        break;
                }
                curQue++;
                attempt = 0;
                msg = "";
            } else {
                attempt++;
                if (attempt < 3) {
                    msg = "Your last answer was not correct! Please try again.";
                } else {
                    msg = "No more Attempt! Correct answer is " + ANSWERS[curQue];
                }
            }

        }
    }

    public int getScore() {
        return score;
    }

    public int getCurQue() {
        return curQue + 1;
    }

    public int getAttempt() {
        return attempt;
    }

    public String getMsg() {
        return msg;
    }

    public String getHint() {
        return new StringBuilder("alert('").append(HINTS[curQue]).append("')").toString();
    }

    public String getAnswer() {
        if (attempt == 3) {
            return String.valueOf(ANSWERS[curQue]);
        }
        return "";
    }

    public String getQuestion() {
        return QUESTIONS[curQue];
    }
}
