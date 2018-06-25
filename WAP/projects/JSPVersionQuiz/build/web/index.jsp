<%-- 
    Document   : index
    Created on : Jun 4, 2018, 5:07:11 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NumberQuiz</title>
        <link href="style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <h1>Have fun with NumberQuiz!</h1>
        <form action="Quiz.do" method="post">
            <div>
                Please enter you age:
                <input name="age" type="text" value="${age}" /><br/>
                <span>${ageHint}</span>
            </div>
            <div>
                <p>Quiz: ${0+quiz.curQue}/5</p>
                <p>Your current score is: ${0+quiz.score}</p>
                <p>Attempt: ${0+quiz.attempt}/3</p>
                <p>Guess the next number in the sequence!</p>
                <p>[${quiz.question},<span>?</span>]</p>
            </div>
            <div>
                Your answer: <input type="text" name="answer" value="${quiz.answer}" />
            </div>
            <p>
                <input name="next" type="submit" value="Next" />
                <input name="reStart" type="submit" value="Restart" />
            </p>
            <p><span>${quiz.msg}</span></p>
            <input type="button" value="Hint?" onclick="${quiz.hint}" />
        </form>

    </body>
</html>
