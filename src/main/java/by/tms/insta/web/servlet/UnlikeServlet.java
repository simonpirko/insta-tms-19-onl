package by.tms.insta.web.servlet;

import by.tms.insta.dao.JDBCPostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Denis Smirnov on 18.05.2023
 */

@WebServlet("/user/unlike")
public class UnlikeServlet extends HttpServlet {

    private final JDBCPostDAO jdbcPostDAO = JDBCPostDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int postId = Integer.parseInt(req.getParameter("postId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        try {
            jdbcPostDAO.unLike(userId);

            resp.sendRedirect(req.getContextPath() + "/viewpost?id=" + postId);

        } catch (SQLException e) {

            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}
