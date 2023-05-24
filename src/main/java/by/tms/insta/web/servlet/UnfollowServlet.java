package by.tms.insta.web.servlet;

import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Denis Smirnov on 24.05.2023
 */
@WebServlet("/user/unfollow")
public class UnfollowServlet extends HttpServlet {

    private final static String USER = "user";
    private final static String FOLLOW_ID = "followId";
    private final UserService userService = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute(USER);

        try {
            int followId = Integer.parseInt(req.getParameter(FOLLOW_ID));

            userService.unfollow(sessionUser.getId(), followId);

            req.setAttribute("username", sessionUser.getUsername());
        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);


        }
    }
}
