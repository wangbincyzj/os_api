package tech.wangbin.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.wangbin.base.api.UserService;
import tech.wangbin.base.support.UserEntity;
import tech.wangbin.base.utils.redis.RedisUtil;
import tech.wangbin.domain.entity.T02User;
import tech.wangbin.domain.mapper.T02UserMapper;
import tech.wangbin.domain.service.IT02UserService;

@Service
@Primary
public class T02UserServiceRedisImpl extends ServiceImpl<T02UserMapper, T02User> implements IT02UserService, UserService {

  @Autowired
  private RedisUtil redisUtil;

  @Override
  public UserEntity getUser(String token) {
    return (UserEntity) redisUtil.get(token);
  }

  @Override
  public String setUser(UserEntity user) {
    String token = SecureUtil.md5(user.getUsername() + DateUtil.now());
    redisUtil.set(user.getUsername(), token);    // 给username绑定了一个token
    redisUtil.set(token, user);  // 给token绑定了一个实体类
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
    } else {
      // 查看是否存在未删除token
      Object o = redisUtil.get(username);
      if(o != null){
        redisUtil.del(((String) o));
      }
      String token = this.setUser(user);
      user.setToken(token);
      return user;
    }
  }

}
