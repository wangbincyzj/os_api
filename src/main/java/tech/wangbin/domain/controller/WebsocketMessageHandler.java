package tech.wangbin.domain.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import tech.wangbin.domain.entity.T02User;
import tech.wangbin.domain.entity.WebsocketReceiveMessage;
import tech.wangbin.domain.entity.WebsocketSendMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WebsocketMessageHandler implements WebSocketHandler {
  // 在线用户列表
  private static final Map<Integer, WebSocketSession> users = new HashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    T02User user = getUser(session);
    log.info("链接成功:{}", user);
    users.put(user.getId(), session);
    this.updateOnlineUsers();
  }

  @Override
  public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
    WebsocketReceiveMessage message = JSONObject.parseObject((String) webSocketMessage.getPayload(), WebsocketReceiveMessage.class);
    T02User fromUser = getUser(webSocketSession);
    Integer to = message.getTo();
    WebSocketSession session = users.get(to);
    if(session!=null){
      session.sendMessage(WebsocketSendMessage.p2pMessage(fromUser,message.getData()));
    }
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
    if (session.isOpen()) {
      session.close();
    }
    users.remove(getUser(session).getId());
    this.updateOnlineUsers();
  }

  @Override
  public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
    users.remove(getUser(webSocketSession).getId());
    this.updateOnlineUsers();
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }


  private T02User getUser(WebSocketSession session) {
    return (T02User) session.getAttributes().get("user");
  }

  private T02User getUser(Integer id) {
    return getUser(users.get(id));
  }

  private List<T02User> getAllUser() {
    List<T02User> list = new ArrayList<>();
    users.values().forEach(session -> {
      list.add(getUser(session));
    });
    return list;
  }

  private void sendTo(Integer id, TextMessage message) throws IOException {
    WebSocketSession webSocketSession = users.get(id);
    webSocketSession.sendMessage(message);
  }

  private void sendAll(TextMessage message) {
    users.values().forEach(session -> {
      try {
        session.sendMessage(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void updateOnlineUsers() {
    this.sendAll(WebsocketSendMessage.updateMessage(getAllUser()));
  }
}
