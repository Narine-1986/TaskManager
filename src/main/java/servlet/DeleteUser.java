package servlet;

import manager.TaskManager;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteUser")
public class DeleteUser extends HttpServlet {

    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.getAttribute("user");
        int userId = Integer.parseInt(req.getParameter("userId"));
        userManager.removeUserById(userId);
        resp.sendRedirect("/managerHome");


//int taskId = Integer.parseInt(req.getParameter("taskId"));
// taskManager.updateTaskByUser(taskId,userId);
//        userManager.removeUserById(userId);
//
    }
}
