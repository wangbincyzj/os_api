package tech.wangbin.blog;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OSApplicationTests {

  @Test
  void contextLoads() {
    System.out.println(SecureUtil.md5("123456dyy"));
  }

}
