package com.example.kindernotification.config.jwt;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter implements Filter {
    // 사용 안 함
    /** 최재은 1/18 (화)
     * - token == headerAuth
     * - id, pw 정상적으로 받아서 로그인이 완료되면 token 발급
     * - 요청 시마다, header 에 Authorization value 값으로 token 을 가져오는데, 해당 user 의 token 이 맞는지 확인만 하면 됨 !
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JwtFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if ("POST".equals(req.getMethod())) {
            System.out.println("POST");
            String headerAuth = req.getHeader("Authorization");
            System.out.println("Authorization >>> " + headerAuth);
            if ("hello".equals(headerAuth)) {
                chain.doFilter(req, res);
            } else {
                PrintWriter writer = res.getWriter();
                writer.println("No Authorization");
            }
        }
    }

}

