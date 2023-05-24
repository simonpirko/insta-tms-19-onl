package by.tms.insta.web.servlet;

import by.tms.insta.entity.Post;
import by.tms.insta.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/explorepage")
public class ExplorePageServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> greatest = postService.findGreatest();
        req.setAttribute("postList", greatest);

        getServletContext().getRequestDispatcher("/pages/explorepage.jsp").forward(req, resp);
    }
}
