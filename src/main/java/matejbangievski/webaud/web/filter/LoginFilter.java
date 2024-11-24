package matejbangievski.webaud.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matejbangievski.webaud.model.User;

import java.io.IOException;

@WebFilter(
        filterName = "login-filter", //Names the filter as "auth-filter".
        urlPatterns = "/*", //Specifies that this filter applies to all URLs (/*).
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}, //Defines when the filter should apply, here for both REQUEST and FORWARD requests.
        initParams = { @WebInitParam(name = "ignore-path", value = "/login"), //Sets initialization parameters for the filter.
                        @WebInitParam(name = "register-path", value = "/register")
        })

public class LoginFilter implements Filter {

    private String ignorePath;
    private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
        registerPath = filterConfig.getInitParameter("register-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //The ServletRequest and ServletResponse objects are cast to HttpServletRequest and HttpServletResponse to access HTTP-specific methods.
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");
        //Patekata na koja e korisnikot
        String path = req.getServletPath();

        if (path.startsWith(ignorePath) || path.startsWith(registerPath) || user != null) {
            // Allow access if the path is login or register or if the user is logged in
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Redirect to login page if user is not logged in and trying to access restricted pages
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
