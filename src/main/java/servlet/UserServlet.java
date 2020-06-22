package servlet;

import manager.TaskManager;
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

@WebServlet(urlPatterns = "/userHome")
public class UserServlet extends HttpServlet {


    private TaskManager taskManager=new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null || user.getUserType()!= UserType.USER){
            resp.sendRedirect("/login.jsp");
        }else{
            List<Task> allTaskByUserId=taskManager.getAllTasksByUserId(user.getId());
            req.setAttribute("tasks", allTaskByUserId);
            req.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(req,resp);

        }


        }

    }

