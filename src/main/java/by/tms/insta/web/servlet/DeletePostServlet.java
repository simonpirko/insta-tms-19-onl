package by.tms.insta.web.servlet;

import by.tms.insta.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/deletepost")
public class DeletePostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        try {
            postService.deletePost(id);
            resp.sendRedirect(req.getContextPath() + "/user/viewprofile?id=" + userId);

        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}

