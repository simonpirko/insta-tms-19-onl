package by.tms.insta.web.servlet;


import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.User;
import by.tms.insta.mapper.UserMapper;
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
        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isPresent()) {
            UserDto byUsernameDto = UserMapper.toDto(byUsername.get());
            if (byUsername.get().getPassword().equals(password)) {

                req.getSession().setAttribute("user", byUsernameDto);

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

