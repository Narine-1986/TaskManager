package servlet;

import manager.TaskManager;
import manager.UserManager;
import models.Task;
import models.User;
import models.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
public class ManagerServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.MANAGER) {
            resp.sendRedirect("/login.jsp");
        } else {
            List<Task> allTasks = taskManager.getAllTasks();
            List<User> allUsers = userManager.getAllUsers();
            req.setAttribute("tasks", allTasks);
            req.setAttribute("users", allUsers);
            req.getRequestDispatcher("/WEB-INF/managerHome.jsp").forward(req, resp);
        }

    }
}

