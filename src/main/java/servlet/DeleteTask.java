package servlet;

import manager.TaskManager;
import models.User;
import models.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteTask")
public class DeleteTask extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        TaskManager taskManager = new TaskManager();
        taskManager.delete(taskId);
        if(user.getUserType()== UserType.MANAGER){
            resp.sendRedirect("/managerHome");
        }else{
            resp.sendRedirect("/userHome");
        }

    }

}
