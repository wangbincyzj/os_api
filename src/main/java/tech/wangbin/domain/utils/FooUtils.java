package tech.wangbin.domain.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class FooUtils {
  public static <T> T mackFromMap(T t, Map<String, Object> map) {
    Field[] fields = t.getClass().getFields();
    Arrays.stream(fields).forEach(field -> {
      try {
        field.set(t, map.get(field.getName()));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });
    return t;
  }
}
