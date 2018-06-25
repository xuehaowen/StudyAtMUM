<%-- 
    Document   : improvedCal
    Created on : Jun 1, 2018, 5:48:53 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ImprovedCalculator" method="post" >
            <!--<input name="plus1" type="text" value='<%=request.getParameter("plus1")%>' />-->
            <input name="plus1" type="text" value="${plus1}" />
            +
            <!--<input name="plus2" type="text" value='<%=request.getParameter("plus2")%>' />-->
            <input name="plus2" type="text" value="${plus2}" />
            =
            <input type="text" value="${re1}"/>
            <br/>
            <!--<input name="mutilply1" type="text" value='<%=request.getParameter("mutilply1")%>' />-->
            <input name="mutilply1" type="text" value="${mutilply1}" />
            *
            <!--<input name="mutilply2" type="text" value='<%=request.getParameter("mutilply2")%>' />-->
            <input name="mutilply2" type="text" value="${mutilply2}" />
            =
            <input type="text" value="${re2}"/>
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
