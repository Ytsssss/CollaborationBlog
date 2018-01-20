package com.ytsssss.collaborationblog.interceptor;

/**
 * Create by Ytsssss on 2018/1/20 13:39
 * 拦截器
 */
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "corsFilter",urlPatterns = "/*")
public class Filter01CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String origin = httpRequest.getHeader("Origin");
        if (origin != null) {
            httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
        }

        /**
         * you probably don't need to change this one, it's indicating what
         * headers you will use. There is no wildcard for this one
         */
        httpServletResponse
          .addHeader("Access-Control-Allow-Headers", "X-Requested-With, X-HTTP-Method-Override, origin, " +
            "content-type, accept, authorization, access-control-request-method, AuthToken");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");

        /**
         * 仔细阅读，请勿更改 even if you don't use head or options, you should let them
         * like this : the CORS system send OPTIONS request to catch cross
         * domain policy, if you don't set it it will be refused
         */
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        /**
         * the max age policy to renew CORS check. Here it's 14 days long
         */
        httpServletResponse.addHeader("Access-Control-Max-Age", "1209600");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }

}

