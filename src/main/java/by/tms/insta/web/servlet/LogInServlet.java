package by.tms.insta.web.servlet;


import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.entity.User;
import by.tms.insta.mapper.UserMapper;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Denis Smirnov on 01.05.2023
 */

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        Optional<User> byUsername;
        try {
            byUsername = userService.findByUsername(username);

            if (byUsername.isPresent()) {

                if (byUsername.get().getPassword().equals(password)) {
                    SessionPrincipalUser sessionPrincipalUser = UserMapper.toSessionPrincipalUserUser(byUsername.get());
                    req.getSession().setAttribute("user", sessionPrincipalUser);
                    req.setAttribute("username", sessionPrincipalUser.getUsername());
                    getServletContext().getRequestDispatcher("/user/account").forward(req, resp);
                } else {
                    req.setAttribute("message", "Wrong password!");
                    getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "Sorry, but User not found!");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}

