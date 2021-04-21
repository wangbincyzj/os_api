package tech.wangbin.domain.controller;

import lombok.extern.slf4j.Slf4j;

import tech.wangbin.base.api.UserService;
import tech.wangbin.domain.service.IT03UserService;
import tech.wangbin.domain.entity.T03User;
import tech.wangbin.base.support.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author WangBin
 * @since 2021-04-20
 */
@Slf4j
@RestController
@RequestMapping("/domain/user")
public class T03UserController extends BaseController<T03User> {

  @Resource
  private UserService userService;

  public T03UserController(IT03UserService service) {
    super(service);
  }

  /**
   * 查询所有或者分页查询
   * 提供page/size作为分页返回
   */
  @Override
  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(HttpServletRequest req, T03User vo,
                                               @RequestParam(value = "asc", required = false) List<String> asc,
                                               @RequestParam(value = "desc", required = false) List<String> desc) {
    return super.getAll(req, vo, asc, desc);
  }


  /**
   * 通过主键查询单条数据
   */
  @Override
  @GetMapping(value = "/{id}")
  public tech.wangbin.base.support.Resp getById(@PathVariable Integer id) {
    return super.getById(id);
  }

  /**
   * 新增
   */
  @Override
  @PostMapping(value = "")
  public tech.wangbin.base.support.Resp insert(@RequestBody T03User vo) {
    return super.insert(vo);
  }

  /**
   * 修改
   */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T03User vo) {
    return super.update(vo);
  }

  /**
   * 删除
   */
  @Override
  @DeleteMapping(value = "/{id}")
  public tech.wangbin.base.support.Resp delete(@PathVariable Integer id) {
    return super.delete(id);
  }


  @PostMapping("/login")
  public Resp login(@RequestBody T03User user) {
    if(user.getUsername()!=null && user.getPassword()!=null){
      UserEntity loginUser = userService.login(user.getUsername(), user.getPassword());
      if(loginUser==null){
        return Resp.err("用户名或者密码错误");
      }else{
        String token = userService.setUser(loginUser);
        return Resp.ok(token);
      }
    }else{
      return Resp.err("");
    }
  }















}