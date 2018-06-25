<%-- 
    Document   : quiz
    Created on : Jun 4, 2018, 5:32:42 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" type="text/css" rel="stylesheet" />
        <title>NumberQuiz</title>
    </head>
    <body>
        <h1>Game Over!</h1>
        <p>Your current score is: ${0+quiz.score}</p>
        <p>Your Grade is</p>
        <h2>${quiz.grade}</h2>
    </body>
</html>
