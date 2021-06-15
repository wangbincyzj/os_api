package tech.wangbin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenInterceptor extends OncePerRequestFilter {
  private final String LOGIN_URI = "/domain/user/login";

  @Resource
  private ApplicationContext context;

  @Resource
  private UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
    boolean isLogin = req.getRequestURI().equals(LOGIN_URI) && req.getMethod().equals("POST");

    // if (!isLogin && !req.getMethod().equals("GET")  && !req.getMethod().equals("OPTIONS")) {
    //   UserEntity user = userService.getUser(req.getHeader("token"));
    //   if(user==null){
    //     throw new RuntimeException("未登录");
    //   }
    // }
    chain.doFilter(req, resp);
  }
}






























