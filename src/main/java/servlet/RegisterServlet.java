package servlet;

import manager.TaskManager;
import manager.UserManager;
import models.User;
import models.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .userType(UserType.valueOf(type))
                .build();
        userManager.register(user);
        resp.sendRedirect("/managerHome");

    }
}
