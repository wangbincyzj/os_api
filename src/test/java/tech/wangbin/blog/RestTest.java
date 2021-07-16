package tech.wangbin.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import tech.wangbin.base.support.UserEntity;

@SpringBootTest
public class RestTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  void test1() {
    ValueOperations val = redisTemplate.opsForValue();
    // Object name = val.get("test");
    val.set("mykey", "myval");
    // System.out.println(name);
    System.out.println(val.get("mykey"));
  }

  @Test
  void testSer() throws JsonProcessingException {
    UserEntity user = new UserEntity();
    user.setUsername("wang");
    String s = new ObjectMapper().writeValueAsString(user);
    System.out.println(s);
    redisTemplate.opsForValue().set("user", user);
    UserEntity user1 = (UserEntity) redisTemplate.opsForValue().get("user");
    System.out.println(user1);

  }
}
