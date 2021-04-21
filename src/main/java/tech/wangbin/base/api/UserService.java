package tech.wangbin.base.api;

import tech.wangbin.base.support.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
  UserEntity getUser(String token);

  String setUser(UserEntity user);

  UserEntity login(String username, String password);
}
