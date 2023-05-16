package by.tms.insta.web.servlet;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("user/createcomment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentMessage = req.getParameter("commentMessage");
        Object[] commentBody = req.getParameter("commentBody").split("&");
        User author = (User) commentBody[0];
        Post post = (Post) commentBody[1];
        LocalDateTime now = LocalDateTime.now();
        Comment build = Comment.builder()
                .setAuthor(author)
                .setPost(post)
                .setMessage(commentMessage)
                .setCreateAt(now)
                .build();
        CommentService instance = CommentService.getInstance();
        try {
            instance.save(build);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
