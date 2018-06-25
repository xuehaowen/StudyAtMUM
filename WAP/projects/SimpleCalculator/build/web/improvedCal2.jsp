<%-- 
    Document   : improvedCal2
    Created on : Jun 2, 2018, 10:30:06 AM
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
        <form action="ImprovedCalculator" method="post" >
            <input name="plus1" type="text"  />
            +
            <input name="plus2" type="text" />
            =
            <input type="text"/>
            <br/>
            <input name="mutilply1" type="text" />
            *
            <input name="mutilply2" type="text" />
            =
            <input type="text"/>
            <br/>
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
