package tech.wangbin.domain.entity;

import lombok.Data;

@Data
public class WebsocketReceiveMessage {
  /**
   * 类型
   */
  private String type;

  /**
   * 接收人ID
   */
  private Integer to;

  /**
   * 数据主体
   */
  private Object data;
}
