package by.tms.insta.web.servlet;


import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Denis Smirnov on 01.05.2023
 */

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (user.getPassword().equals(password)) {

                req.getSession().setAttribute("user", user);

                resp.sendRedirect("/");
            } else {
                req.setAttribute("message", "Wrong password!");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Sorry, but User not found!");
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}

