package servlet;

import manager.UserManager;
import models.User;
import models.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            StringBuilder msg = new StringBuilder();
            if (email == null || email.length() == 0) {
                msg.append("email field is reqired <br>");
            } else if (userManager.getByEmail(email) != null) {
                msg.append("email already exists <br>");
            }
            if (password == null || password.length() == 0) {
                msg.append("password field is reqired <br>");
            }
            if (msg.toString().equals("")) {
                User user = userManager.getByEmailAndPassword(email, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    if (user.getUserType() == UserType.USER) {
                        resp.sendRedirect("/userHome");
                    } else {
                        resp.sendRedirect("/managerHome");
                    }
                }

            }

            req.getSession().setAttribute("msg", msg.toString());
            resp.sendRedirect("/");
        }
    }





