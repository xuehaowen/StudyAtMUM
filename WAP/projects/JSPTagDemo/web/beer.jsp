<%-- 
    Document   : beer
    Created on : Jun 5, 2018, 4:25:29 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>
            <%--        
                    <%
                        List styles = (List) request.getAttribute("styles");
                        Iterator it = styles.iterator();
                        while (it.hasNext()) {
                            out.print("<br >try : " + it.next());
                        }
                    %>
            --%>
            <c:forEach items="${styles}" var="style" >
                try: ${style}<br/>
            </c:forEach>
        </p>
    </body>
</html>
