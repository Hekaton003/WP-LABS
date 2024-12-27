package mk.ukim.finki.wp.lab.web.filter;

/*
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;

import java.io.IOException;

@WebFilter(filterName = "songFilter",urlPatterns = "/*",dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD},
initParams = {@WebInitParam(name = "ignore-path",value = "/listSongs")})
public class SongFilter implements Filter {
    private String ignorePath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Song song = (Song) request.getSession().getAttribute("song");
        String path = request.getServletPath();
        if (song == null && !path.startsWith(ignorePath)) {
            response.sendRedirect("/listSongs");
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
*/