<%-- 
    Document   : index
    Created on : Jun 5, 2018, 3:56:05 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/TDLDemo" prefix="mytag" %>
<%--<%@page import="java.util.*" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Beer Selection Page</h1>
        <mytag:Head thecolor="red" words="hello world!" />
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
