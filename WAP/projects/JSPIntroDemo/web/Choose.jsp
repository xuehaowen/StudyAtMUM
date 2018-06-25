<%-- 
    Document   : Choose
    Created on : Jun 4, 2018, 4:13:08 PM
    Author     : toby_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Choose</title>
    </head>
    <body>
        <form method='post' action='ChooseServlet'>
            <p>Is JSP cool?</p>
            <% String choice1 = (String) request.getAttribute("radioJSPCool_1");
                String choice2 = (String) request.getAttribute("radioJSPCool_2");%>
            <input type='radio' value='1' name='radioJSPCool' 
                   <%= choice1%>><span>Yes</span><br/>
            <input type='radio' value='0' name='radioJSPCool'
                   <%= choice2%>><span>No</span><br/>
            <input type='submit' name='btnSubmit' value='Submit'/>
            <p>Is JSF way cool?</p>
            <input type='radio' value='1' name='JSFwayCool' ${JSFwayCool_1} ><span>Yes</span><br/>
            <input type='radio' value='0' name='JSFwayCool' ${JSFwayCool_2} ><span>No</span><br/>
            <input type='submit' name='btnSubmit' value='Submit'/>
            <p>Is the moon made of cheese?</p>
            <input type='radio' value='1' name='moonCheese' ${moonCheese.yesCheck} ><span>Yes</span><br/>
            <input type='radio' value='0' name='moonCheese' ${moonCheese.noCheck}><span>No</span><br/>
            <input type='submit' name='btnSubmit' value='Submit'/>
        </form>
    </body>
</html>

