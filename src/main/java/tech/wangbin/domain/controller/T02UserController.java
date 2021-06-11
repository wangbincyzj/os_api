package tech.wangbin.domain.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import tech.wangbin.domain.service.IT02UserService;
import tech.wangbin.domain.entity.T02User;
import tech.wangbin.base.support.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tech.wangbin.domain.service.impl.T02UserServiceImpl;

import java.util.List;


/**
 * @author WangBin
 * @since 2021-06-11
 */
@Slf4j
@RestController
@RequestMapping("/domain/user")
public class T02UserController extends BaseController<T02User> {
  @Autowired
  private T02UserServiceImpl userService;

  public T02UserController(IT02UserService service) {
    super(service);
  }

  /**
   * 查询所有或者分页查询
   * 提供page/size作为分页返回
   */
  @Override
  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(HttpServletRequest req, T02User vo,
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
  public tech.wangbin.base.support.Resp insert(@RequestBody T02User vo) {
    return super.insert(vo);
  }

  /**
   * 修改
   */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T02User vo) {
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
  public Resp login(@RequestBody T02User user) {
    T02User loginUser = userService.login(user.getUsername(), user.getPassword());
    if(loginUser!=null){
      return Resp.ok(loginUser);
    }else {
      return Resp.err("用户未找到");
    }
  }
}