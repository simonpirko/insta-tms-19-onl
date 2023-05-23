package by.tms.insta.web.servlet;

import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/user/searchuser")
public class SearchUserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        try {
            Optional<User> byUsername = userService.findByUsername(username);

            if (byUsername.isPresent()){

                resp.sendRedirect(req.getContextPath() + "/user/profile?username=" + username);

            } else {

                req.setAttribute("userNotFound", "User not found");
                getServletContext().getRequestDispatcher("/pages/explorepage.jsp").forward(req, resp);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
