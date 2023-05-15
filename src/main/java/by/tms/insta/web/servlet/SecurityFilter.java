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

        if (req.getSession().getAttribute("user") == null) {
            getServletContext().getRequestDispatcher("/pages/filter.jsp").forward(req, res);
        } else {
            chain.doFilter(req, res);
        }

    }
}
