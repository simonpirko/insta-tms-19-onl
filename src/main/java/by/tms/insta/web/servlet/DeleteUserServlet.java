package by.tms.insta.web.servlet;

import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Denis Smirnov on 12.05.2023
 */
@WebServlet("/user/account")
public class DeleteUserServlet extends HttpServlet {
    private final String USER = "user";
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute(USER);

        try {

            userService.deleteById(sessionUser.getId());
            resp.sendRedirect("/register");

        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}
