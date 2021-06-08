package tech.wangbin.domain.config;


import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
@PropertySource("classpath:application.yaml")
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
  private String endpoint;
  private int port;
  private String accessKey;
  private String secretKey;

  @Bean
  public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
    return new MinioClient(endpoint, port, accessKey, secretKey, false);
  }
}
