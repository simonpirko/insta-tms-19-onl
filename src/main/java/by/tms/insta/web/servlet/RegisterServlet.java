package by.tms.insta.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
//        if (service.checkEmail(email)) {
//            req.setAttribute("emailUsed", "this email already in used");
//            req.getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
//        } else if (service.checkUsername(username)){
//            req.setAttribute("usernameUsed", "this username already in used");
//            req.getRequestDispatcher("/pages/reg.jsp").forward(req, resp);
//        } else {
//            User user = new User(name, username, email, password);
//            service.save(user);
//            resp.sendRedirect("/");
//        }
    }
}
