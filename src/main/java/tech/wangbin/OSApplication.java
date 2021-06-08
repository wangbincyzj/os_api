package tech.wangbin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@MapperScan("tech.wangbin.blog.mapper")
@MapperScan("tech.wangbin.domain.mapper")
@Transactional
public class OSApplication {

  public static void main(String[] args) {
    SpringApplication.run(OSApplication.class, args);
  }

}
