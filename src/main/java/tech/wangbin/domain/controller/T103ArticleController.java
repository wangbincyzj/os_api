package tech.wangbin.domain.controller;

import lombok.extern.slf4j.Slf4j;

import tech.wangbin.domain.service.IT103ArticleService;
import tech.wangbin.domain.entity.T103Article;
import tech.wangbin.base.support.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author WangBin
 * @since 2021-07-15
 */
@Slf4j
@RestController
@RequestMapping("/domain/article")
public class T103ArticleController extends BaseController<T103Article> {

  public T103ArticleController(IT103ArticleService service) {
    super(service);
  }

  /**
   * 查询所有或者分页查询
   * 提供page/size作为分页返回
   */
  @Override
  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(HttpServletRequest req, T103Article vo,
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
  @PostMapping(value = "")
  public tech.wangbin.base.support.Resp insert(@RequestBody T103Article vo, HttpServletRequest req) {
    UserEntity user = (UserEntity) req.getAttribute("user");
    vo.setUId(user.getId());
    return super.insert(vo);
  }

  /**
   * 修改
   */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T103Article vo) {
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
}