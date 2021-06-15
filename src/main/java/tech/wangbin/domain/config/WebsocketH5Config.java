package tech.wangbin.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import tech.wangbin.domain.controller.WebsocketMessageHandler;
import tech.wangbin.interceotors.WebSocketMessageInterceptor;


@Configuration
@EnableWebSocket
public class WebsocketH5Config implements WebSocketConfigurer {
  @Autowired
  private HandshakeInterceptor handshakeInterceptor;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new WebsocketMessageHandler(), "/websocket/message/{ID}")
      .setAllowedOrigins("*")
      .addInterceptors(handshakeInterceptor);
  }
}
