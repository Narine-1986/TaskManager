<%--
  Created by IntelliJ IDEA.
  User: Sirius Plus
  Date: 15.06.2020
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%
    String msg="";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

%>
<p style="color: red">
    <%=msg%>

</p>
Login:
<form action="/login" method="post">
    <input type="text" name="email" placeholder="please input email"/> <br>
    <input type="password" name="password" placeholder="please input password"/> <br>
    <input type="submit" value="Login">
</form>


</body>
</html>
