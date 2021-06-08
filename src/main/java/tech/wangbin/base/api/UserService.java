package tech.wangbin.base.api;

import tech.wangbin.base.support.UserEntity;



public interface UserService {
  UserEntity getUser(String token);

  String setUser(UserEntity user);

  UserEntity login(String username, String password);
}
