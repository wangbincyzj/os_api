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
public class WebsocketBallGameHandler implements WebSocketHandler {
  // 在线用户列表
  private static final Map<Integer, WebSocketSession> users = new HashMap<>();
  private static final List<Integer> waitQueue = new ArrayList<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    T02User user = getUser(session);
    log.info("链接成功:{}", user.getUsername());
    users.put(user.getId(), session);
    sendTo(user.getId(), WebsocketSendMessage.textMessage(null, null, "connected"));
    this.updateOnlineUsers();
  }

  @Override
  public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
    WebsocketReceiveMessage message = JSONObject.parseObject((String) webSocketMessage.getPayload(), WebsocketReceiveMessage.class);
    // 获取信息来源
    T02User fromUser = getUser(webSocketSession);
    // 获取发送者
    Integer to = message.getTo();
    if (to == 0) {
      waitQueue.add(fromUser.getId());
      sendTo(fromUser.getId(), WebsocketSendMessage.textMessage(null, null, "waiting"));
      log.info("进入匹配:{}", fromUser.getUsername());
      if (waitQueue.size() == 2) {
        this.start();
      }
      return;
    }
    WebSocketSession session = users.get(to);
    if (session != null) {
      T02User toUser = getUser(session);
      log.info("发送: {}, 接受: {}, 内容:{}", fromUser.getUsername(), toUser.getUsername(), message.getData());
      session.sendMessage(WebsocketSendMessage.p2pMessage(fromUser, message.getData()));
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
    T02User user = getUser(webSocketSession);
    log.info("断开连接:{}", user.getUsername());
    Integer id = user.getId();
    users.remove(id);
    int i = waitQueue.indexOf(id);
    if(i != -1){
      waitQueue.remove(i);
      log.info("匹配队列移除:{}, 匹配队列长度:{}", user.getUsername(), waitQueue.size());
    }
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

  private void start() throws IOException {
    T02User p1 = getUser(waitQueue.get(0));
    T02User p2 = getUser(waitQueue.get(1));
    log.info("匹配成功:{},{}", p1.getUsername(), p2.getUsername());
    this.sendTo(p1.getId(), WebsocketSendMessage.textMessage(p2, p2, "playerFound"));
    this.sendTo(p2.getId(), WebsocketSendMessage.textMessage(p1, p1, "playerFound"));
    waitQueue.clear();
    log.info("匹配队列清空");
  }

  private void updateOnlineUsers() {
    this.sendAll(WebsocketSendMessage.updateMessage(getAllUser()));
  }
}
