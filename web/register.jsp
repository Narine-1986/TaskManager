<%--
  Created by IntelliJ IDEA.
  User: Sirius Plus
  Date: 17.06.2020
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

Register:
<form action="/register" method="post">
    <input type="text" name="name" placeholder="please input name"/> <br>
    <input type="text" name="surname" placeholder="please input surname"/> <br>
    <input type="text" name="email" placeholder="please input email"/> <br>
    <input type="password" name="password" placeholder="please input password"/> <br>
    <input type="submit" value="Register">
</form>
</body>
</html>
