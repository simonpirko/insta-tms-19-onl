package by.tms.insta.web.servlet;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.entity.User;
import by.tms.insta.mapper.UserMapper;
import by.tms.insta.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Denis Smirnov on 18.05.2023
 */
@WebServlet("/post/like")
public class LikeServlet extends HttpServlet {
    private final static String USER = "user";
    private final static String POST_ID = "postId";
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute(USER);

        try {
            int postId = Integer.parseInt(req.getParameter(POST_ID));
            Optional<Post> post = postService.findPostById(postId);
            if (post.isPresent()) {

                postService.like(sessionUser.getId(), postId);
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
