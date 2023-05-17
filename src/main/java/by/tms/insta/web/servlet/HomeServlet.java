package by.tms.insta.web.servlet;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.service.PostService;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/user/home")
public class HomeServlet extends HttpServlet {
    private int countOfPages = 0;
    private final UserService userService = UserService.getInstance();
    private final PostService postService = PostService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User sessionUser = (User) req.getSession().getAttribute("user");

        try {

            List<User> users = userService.extractFollowed(sessionUser.getId());
            int postsPerPage = 5;

            for (User user : users) {
                int countOfPagesWithPosts = postService.getCountOfPagesWithPosts(user, postsPerPage);
                countOfPages = countOfPages + countOfPagesWithPosts;
            }

            req.setAttribute("countOfPages", countOfPages);

            String page = req.getParameter("page");
            if (page == null) {
                req.setAttribute("page", 1);
            }

            int paginationOffset = (Integer.parseInt(page) - 1) * postsPerPage;

            List<Post> postList = postService.getFollowedUsersPosts(sessionUser.getId(), postsPerPage, paginationOffset);
            req.setAttribute("postList", postList);

            req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);


        } catch (SQLException e) {
            req.setAttribute("errormessage", "Something went wrong on our side.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }

    }
}
