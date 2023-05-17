package by.tms.insta.web.servlet;

import by.tms.insta.entity.Post;
import by.tms.insta.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/user/editpost")
public class EditPostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/editPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (id != null) {

            try {
                int postId = Integer.parseInt(id);
                Optional<Post> post = postService.findPostById(postId);

                if (post.isPresent()) {

                    req.setAttribute("post", post);

                    String newDescription = req.getParameter("newDescription");
                    String newImage = req.getParameter("newImage");
                    postService.updatePost(postId, newImage, newDescription);

                   resp.sendRedirect(req.getContextPath() + "/viewpost?id=" + id);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
