package tech.wangbin.base.support;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserEntity extends BaseEntity implements Serializable {
  private Integer id;

  private String username;

  private String password;

  private String role;
}
