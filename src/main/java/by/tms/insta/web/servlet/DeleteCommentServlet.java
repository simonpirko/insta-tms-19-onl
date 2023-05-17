package by.tms.insta.web.servlet;

import by.tms.insta.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/deletecomment")
public class DeleteCommentServlet extends HttpServlet {
    private final CommentService commentService = CommentService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int commentId = Integer.parseInt(req.getParameter("commentId"));

        try {

            int postId = commentService.findPostIdByCommentId(commentId);
            commentService.deleteById(commentId);

            resp.sendRedirect(req.getContextPath() + "/viewpost?id=" + postId);

        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}