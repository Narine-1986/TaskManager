package servlet;

import manager.TaskManager;
import models.Status;
import models.Task;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    TaskManager taskManager=new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String description=req.getParameter("description");
        String date=req.getParameter("date");
        String status=req.getParameter("status");
        int userId=Integer.parseInt(req.getParameter("user_id"));

        taskManager.add(Task.builder()
                .name(name)
                .description(description)
                .deadline(DateUtil.convertStringToDate(date))
                .status(Status.valueOf(status))
                .userId(userId)
                .build());
        resp.sendRedirect("/managerHome");
    }
}
