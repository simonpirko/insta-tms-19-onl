package by.tms.insta.web.servlet;

import by.tms.insta.entity.User;
import by.tms.insta.service.PostService;
import by.tms.insta.entity.SessionPrincipalUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/post/delete")
public class DeletePostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        String username = sessionUser.getUsername();

        try {
            postService.deletePost(id);
            resp.sendRedirect(req.getContextPath() + "/account?username=" + username);
        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}


