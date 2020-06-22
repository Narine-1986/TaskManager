<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Task" %><%--
  Created by IntelliJ IDEA.
  User: Sirius Plus
  Date: 17.06.2020
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Welcome User home
<br>
<a href="/logout">Logout</a>

<div>
<%
    User user = (User) request.getAttribute("user");
    List<User> users = (List<User>) request.getAttribute("allUsers");
    if(users!=null){
%>

Welcome <%= user.getName()%> <%= user.getSurname()%>

<%}%>
</div>

<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>

    <div>
        All Tasks:<br>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Description</td>
                <td>Deadline</td>
                <td>Status</td>
                <td>User</td>
                <td>Update Status</td>
                <td>Delete Task</td>
            </tr>
            <%
                for (Task task : tasks) {%>
            <tr>
                <td><%=task.getName()%>
                </td>
                <td><%=task.getDescription()%>
                </td>
                <td><%=task.getDeadline()%>
                </td>
                <td><%=task.getStatus().name()%>
                </td>
                <td><%=task.getUser().getName()+" "+ task.getUser().getSurname()%>
                </td>

                <td>
                  <form action="/changeTaskStatus" method="post">
                      <input type="hidden" name="taskId" value="<%=task.getId()%>">
                      <select name="status">
                          <option value="NEW">NEW</option>
                          <option value="IN_PROGRESS">IN_PROGRESS</option>
                          <option value="FINISHED">FINISHED</option>
                      </select> <input type="submit" value="OK">
                  </form>
                </td>
                <td>
                    <form action="/deleteTask" method="post">
                        <input type="hidden" name="taskId" value="<%=task.getId()%>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>


</body>
</html>
