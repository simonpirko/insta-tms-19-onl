package by.tms.insta.web.servlet;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.CommentService;
import by.tms.insta.service.PostService;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/user/addcomment")
public class AddCommentServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    private final CommentService commentService = CommentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User sessionUser = (User) req.getSession().getAttribute("user");

        try {

            int postId = Integer.parseInt(req.getParameter("postId"));
            Optional<Post> post = postService.findPostById(postId);

            if (post.isPresent()) {
                String commentMessage = req.getParameter("commentMessage");
                LocalDateTime now = LocalDateTime.now();
                Comment build = Comment.builder()
                        .setAuthor(sessionUser)
                        .setPost(post.get())
                        .setMessage(commentMessage)
                        .setCreateAt(now)
                        .build();
                commentService.save(build);

                resp.sendRedirect(req.getContextPath() + "/viewpost?id=" + post.get().getId());

            } else {
                req.setAttribute("errormessage", "We can't found post.");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }
}
