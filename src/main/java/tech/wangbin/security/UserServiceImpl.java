package tech.wangbin.security;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;
import tech.wangbin.domain.entity.T03User;
import tech.wangbin.domain.service.IT03UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @Resource
  private IT03UserService userService;

  private MD5 md5 = new MD5();

  private Map<String, UserEntity> userMap = new HashMap<>();
  private Map<String, String> userTokenMap = new HashMap<>();

  @Override
  public UserEntity getUser(String token) {
    return userMap.get(token);
  }

  @Override
  public String setUser(UserEntity user) {
    String oldToken = userTokenMap.get(user.getUsername());
    if(oldToken != null){
      userMap.remove(oldToken);
    }
    String unique = user.getUsername() + new Date().toString();
    String token = md5.digestHex16(unique);
    userTokenMap.put(user.getUsername(), token);
    userMap.put(token, user);
    return token;
  }

  @Override
  public UserEntity login(String username, String password) {
    QueryWrapper<T03User> qw = new QueryWrapper<>();
    T03User t03User = new T03User();
    t03User.setUsername(username);
    t03User.setPassword(password);
    return userService.getOne(qw);
  }
}
