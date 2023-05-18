package by.tms.insta.web.servlet;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.User;
import by.tms.insta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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

        if (hasChanges(newName, newEmail, newImage, newPassword, repeatedNewPassword)) {
            try {
                UserDto userDto = (UserDto) req.getSession().getAttribute("user");
                Optional<User> optionalUser = userService.findByUsername(userDto.getUsername());

                if (newName.isEmpty()) {
                    System.out.println("null name");
                }
                if (newEmail.isEmpty()) {
                    System.out.println("null email");
                }
                if (newImage.isEmpty()) {
                    System.out.println("null image");
                }
            } catch (SQLException e) {
                req.setAttribute("errormessage", "Something went wrong on our side.");
                getServletContext().getRequestDispatcher("/pages/error.jsp").forward(req, resp);
            }
        }
    }

    private boolean hasChanges(String name, String email, String image, String password, String repeatedPassword){
        return !(name.isEmpty() && email.isEmpty() && image.isEmpty() && password.isEmpty() && !password.equals(repeatedPassword));
    }
}
