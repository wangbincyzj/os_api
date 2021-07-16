package tech.wangbin.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.Resp;
import tech.wangbin.base.support.UserEntity;
import tech.wangbin.domain.service.impl.T02UserServiceRedisImpl;

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

  @Autowired
  @Qualifier("t02UserServiceRedisImpl")
  private UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
    boolean isLogin = req.getRequestURI().equals(LOGIN_URI) && req.getMethod().equals("POST");
    UserEntity user = userService.getUser(req.getHeader("token"));
    req.setAttribute("user", user);
    if (!isLogin && !req.getMethod().equals("GET")  && !req.getMethod().equals("OPTIONS")) {
      if(user==null){
        createErr(resp);
        return;
      }
    }
    chain.doFilter(req, resp);
  }


  private void createErr(HttpServletResponse resp) throws IOException {
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json;charset=UTF-8");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    resp.setHeader("Access-Control-Allow-Methods",
      "GET,POST,PUT,DELETE,OPTIONS");
    resp.setHeader("Access-Control-Allow-Credentials", "true");
    resp.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
    resp.setHeader("Access-Control-Allow-Credentials", "true");
    String jsonString = new ObjectMapper().writeValueAsString(Resp.limitErr());
    resp.getWriter().write(jsonString);
    resp.flushBuffer();
  }
}






























