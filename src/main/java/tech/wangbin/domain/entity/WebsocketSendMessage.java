package tech.wangbin.domain.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.web.socket.TextMessage;

import java.util.Date;
import java.util.List;

@Data
public class WebsocketSendMessage {
  private static final String P2P_MESSAGE = "p2pMessage";
  private static final String UPDATE = "update";
  private static final String P2P_GAME = "p2pGame";

  /**
   * 事件类型
   */
  private String type;

  /*
  * 发送人ID
  * */
  private T02User from;

  /**
   * 数据主体
   */
  private Object data;

  /**
   * 时间戳
   */
  private long timestamp;

  public static TextMessage textMessage(T02User fromId, Object data, String type){
    WebsocketSendMessage message = new WebsocketSendMessage();
    message.setFrom(fromId);
    message.setData(data);
    message.setType(type);
    message.setTimestamp(new Date().getTime());
    return new TextMessage(JSONObject.toJSONString(message));
  }


  public static TextMessage p2pMessage(T02User fromId, Object data) {
    return textMessage(fromId, data, P2P_MESSAGE);
  }

  public static TextMessage updateMessage(List<T02User> users) {
    WebsocketSendMessage message = new WebsocketSendMessage();
    message.setType(UPDATE);
    message.setData(users);
    message.setTimestamp(new Date().getTime());
    return new TextMessage(JSONObject.toJSONString(message));
  }


}


