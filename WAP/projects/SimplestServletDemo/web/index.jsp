<%-- 
    Document   : index
    Created on : Jun 1, 2018, 4:24:18 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href='Simplest'>SimplestServletDemo</a>
        <a href='HelloWorld'>HelloWorld</a>
        <h1>Beer Selection Page</h1>
        <form action="SelectBeer.do" method="post">
            <p>Select beer characteristics</p>
            Color:
            <select name="color" size="1">
                <option selected value="light">light</option>
                <option value="dark">dark</option>
                <option value="amber">amber</option>
                <option value="brown">brown</option>
            </select>
            <br/><br/>
            <input type="submit"/>
        </form>
    </body>
</html>
