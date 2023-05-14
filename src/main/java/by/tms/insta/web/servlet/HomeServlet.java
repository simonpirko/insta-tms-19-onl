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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private int offset;
    private final PostService postService = PostService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.offset = 0;
        List<Post> postList = postService.findAll();
        req.setAttribute("listSize", postList.size());
        req.setAttribute("offset", offset);
        req.setAttribute("postList", postList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        offset = Integer.parseInt(req.getParameter("offset"));
        List<Post> postList = postService.findAll();
        req.setAttribute("listSize", postList.size());
        req.setAttribute("offset", offset);
        req.setAttribute("postList", postList);

        req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);
    }
}
