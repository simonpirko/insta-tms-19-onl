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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String NAME = "name";
    private final static String EMAIL = "email";
    private final static String AVATAR = "avatar";

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String name = req.getParameter(NAME);
        String email = req.getParameter(EMAIL);
        String avatar = req.getParameter(AVATAR);

        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isEmpty()) {
            User user = User.builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setName(name)
                    .setEmail(email)
                    .setAvatar(avatar)
                    .build();
            userService.save(user);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("message", "User with this UserName already exists! Please, try with another name!");
            getServletContext().getRequestDispatcher("/pages/register.jsp").forward(req, resp);

        }


    }
}
