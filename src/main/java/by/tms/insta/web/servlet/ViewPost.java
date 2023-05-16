package by.tms.insta.web.servlet;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.service.CommentService;
import by.tms.insta.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/user/viewpost")
public class ViewPost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PostService postService = PostService.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Optional<Post> postById = postService.findPostById(id);
            if (postById.isPresent()) {

                req.setAttribute("post", postById);
                req.setAttribute("likes", postService.getCountOfLikes(id));

                CommentService commentService = CommentService.getInstance();
                int commentPerPage = 5;
                int countOfPages = commentService.getCountOfPages(id, commentPerPage);
                req.setAttribute("countOfPages", countOfPages);

                String page = req.getParameter("page");
                if (page == null){
                    req.setAttribute("page", 1);
                }
                int paginationOffset = Integer.parseInt(page) * commentPerPage;

                List<Comment> commentList = commentService.findByPostId(id, paginationOffset, commentPerPage);
                req.setAttribute("commentList", commentList);

                getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);

            } else {
                req.setAttribute("errormessage", "Post not found!");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
