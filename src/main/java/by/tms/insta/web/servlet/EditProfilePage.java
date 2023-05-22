package by.tms.insta.web.servlet;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.entity.User;
import by.tms.insta.mapper.UserMapper;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrei Lisouski (Andrlis) - 18/05/2023 - 1:32
 */
@WebServlet("/user/account/edit")
public class EditProfilePage extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/editprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("newName");
        String newEmail = req.getParameter("newEmail");
        String newImage = req.getParameter("newImage");
        String newPassword = req.getParameter("newPassword");
        String repeatedNewPassword = req.getParameter("repeatNewPassword");

        if(!newPassword.equals(repeatedNewPassword)){
            req.setAttribute("errormessage", "Passwords are not equal.");
            getServletContext().getRequestDispatcher("/pages/editprofile.jsp").forward(req, resp);
        }


        SessionPrincipalUser sessionUser = (SessionPrincipalUser) req.getSession().getAttribute("user");
        User updatedUser = User.builder()
                .setId(sessionUser.getId())
                .setUsername(sessionUser.getUsername())
                .setName(newName)
                .setEmail(newEmail)
                .setAvatar(newImage)
                .setPassword(newPassword)
                .build();


        userService.updateUserProfile(UserMapper.toUser(sessionUser), updatedUser);

        req.setAttribute("username", sessionUser.getUsername());
        getServletContext().getRequestDispatcher("/user/account").forward(req, resp);
    }

}
