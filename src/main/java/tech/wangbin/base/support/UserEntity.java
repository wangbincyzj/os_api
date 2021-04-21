package tech.wangbin.base.support;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity{
  private Integer id;

  private String username;

  private String password;

  private String role;
}
