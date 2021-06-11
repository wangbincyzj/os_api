package tech.wangbin.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;
import tech.wangbin.domain.entity.T02User;
import tech.wangbin.domain.mapper.T02UserMapper;
import tech.wangbin.domain.service.IT02UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WangBin
 * @since 2021-06-11
 */
@Service
public class T02UserServiceImpl extends ServiceImpl<T02UserMapper, T02User> implements IT02UserService, UserService {
  private final Map<String, UserEntity> map = new HashMap<>();

  @Override
  public UserEntity getUser(String token) {
    return map.get(token);
  }

  @Override
  public String setUser(UserEntity user) {
    String token = SecureUtil.md5(user.getUsername() + DateUtil.now());
    map.put(token, user);
    return token;
  }

  @Override
  public T02User login(String username, String password) {
    QueryWrapper<T02User> qw = new QueryWrapper<>();
    qw.eq("username", username);
    qw.eq("password", SecureUtil.md5(password));
    T02User user = this.getOne(qw);
    if (user == null) {
      return null;
    }else{
      String token = this.setUser(user);
      user.setToken(token);
      return user;
    }
  }
}
