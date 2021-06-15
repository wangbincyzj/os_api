package tech.wangbin.interceotors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;
import tech.wangbin.domain.entity.T02User;

import java.util.Map;

@Slf4j
@Component
public class WebSocketMessageInterceptor implements HandshakeInterceptor {
  @Autowired
  private UserService userService;

  @Override
  public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
    try {
      String[] strings = serverHttpRequest.getURI().toString().split("/");
      String token = strings[strings.length - 1];
      T02User user = (T02User) userService.getUser(token);
      if(user == null){
        throw new RuntimeException("用户未找到");
      }else{
        map.put("user", user);
      }
      return true;
    }catch (Exception e){
      e.printStackTrace();
      log.error(e.toString());
      return false;
    }
  }

  @Override
  public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

  }
}
