package by.tms.insta.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        Optional<User> byEmail = service.findByEmail(email);

//        if (byUsername.isPresent()){
//            User user = byUsername.get();
//            if (user.getPassword().equals(password)){
//                req.getSession().setAttribute("user", user);
//                resp.sendRedirect("/");
//            }else {
//                req.setAttribute("loginMessage","Incorrect username or password");
//                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
//            }
//        } else {
//            req.setAttribute("loginMessage","Incorrect username or password");
//            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
//        }
    }
}

