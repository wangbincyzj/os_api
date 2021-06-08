package tech.wangbin.security;

import org.springframework.stereotype.Service;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public UserEntity getUser(String token) {
    return null;
  }

  @Override
  public String setUser(UserEntity user) {
    return null;
  }

  @Override
  public UserEntity login(String username, String password) {
    return null;
  }
}
