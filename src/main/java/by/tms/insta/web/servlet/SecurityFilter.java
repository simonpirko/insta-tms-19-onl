package by.tms.insta.web.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

//        getServletContext().getRequestDispatcher("/pages/filter.jsp").forward(req, res);

        if (req.getSession().getAttribute("user") == null) {
//            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            res.sendRedirect("/pages/filter.jsp");
        } else {
            chain.doFilter(req, res);
        }

    }
}
