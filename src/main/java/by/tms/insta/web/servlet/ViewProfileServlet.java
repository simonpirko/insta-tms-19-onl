package by.tms.insta.web.servlet;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.mapper.UserMapper;
import by.tms.insta.service.PostService;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrei Lisouski (Andrlis) - 10/05/2023 - 0:03
 */
@WebServlet("/account")
public class ViewProfileServlet extends HttpServlet {

    private final static String USERNAME_PARAMETER = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = UserService.getInstance();

        String username = req.getParameter(USERNAME_PARAMETER);
        Optional<User> account = userService.findByUsername(username);

        if (account.isPresent()) {
            UserDto accountDto = UserMapper.toDto(account.get());
            req.setAttribute("account", accountDto);

            int followersCnt = userService.extractCountOfFollowers(account.get().getId());
            int followingCnt = userService.extractCountOfFollowed(account.get().getId());

            req.setAttribute("followersCnt", followersCnt);
            req.setAttribute("followingCnt", followingCnt);

            //TODO
            //Need to add post extraction and pagination
            PostService postService = PostService.getInstance();
            List<Post> posts = postService.getPostsByUser(account.get());
            req.setAttribute("posts", posts);

            getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
        } else {
            req.setAttribute("errormessage", "User not found.");
            getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
