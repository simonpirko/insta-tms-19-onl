package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String login = new String("/login");
        String register = new String("/register");

        List<String> excludedUrls = new ArrayList<>(Arrays.asList(login, register));

        String path = ((HttpServletRequest) req).getServletPath();

        if(!excludedUrls.contains(path))
        {
            getServletContext().getRequestDispatcher("/pages/filter.jsp").forward(req, res);
        }
        chain.doFilter(req, res);
    }

    }

