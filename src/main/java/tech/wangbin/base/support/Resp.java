package tech.wangbin.base.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp {
  private int code;
  private String msg;
  private Object data;

  public static Resp ok() {
    return new Resp(0, "success", null);
  }

  public static Resp ok(String msg) {
    return new Resp(0, msg, null);
  }

  public static Resp ok(Object obj) {
    return new Resp(0, "success", obj);
  }

  public static Resp ok(String msg, Object obj) {
    return new Resp(0, msg, obj);
  }

  public static Resp err() {
    return new Resp(1, "fail", null);
  }

  public static Resp err(String msg) {
    return new Resp(1, msg, null);
  }

  public static Resp err(Object obj) {
    return new Resp(1, "fail", obj);
  }

  public static Resp err(int code, String msg, Object obj) {
    return new Resp(code, msg, obj);
  }

  public static Resp limitErr() {
    return new Resp(999, "访问限制", null);
  }

  public static Resp limitErr(String msg) {
    return new Resp(999, msg, null);
  }
}