package tech.wangbin.domain.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.web.socket.TextMessage;

import java.util.List;

@Data
public class WebsocketSendMessage {
  private static final String P2P_MESSAGE = "p2pMessage";
  private static final String UPDATE = "update";

  /**
   * 事件类型
   */
  private String type;

  /*
  * 发送人ID
  * */
  private Integer from;

  /**
   * 数据主体
   */
  private Object data;


  public static TextMessage p2pMessage(Integer fromId, Object data) {
    WebsocketSendMessage message = new WebsocketSendMessage();
    message.setFrom(fromId);
    message.setData(data);
    message.setType(P2P_MESSAGE);
    return new TextMessage(JSONObject.toJSONString(message));
  }

  public static TextMessage updateMessage(List<T02User> users) {
    WebsocketSendMessage message = new WebsocketSendMessage();
    message.setType(UPDATE);
    message.setData(users);
    return new TextMessage(JSONObject.toJSONString(message));
  }
}

