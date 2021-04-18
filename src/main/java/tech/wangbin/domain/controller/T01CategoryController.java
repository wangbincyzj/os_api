package tech.wangbin.domain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.wangbin.base.support.BaseController;
import tech.wangbin.domain.entity.T01Category;
import tech.wangbin.domain.service.IT01CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author WangBin
 * @since 2021-04-18
 */
@Slf4j
@RestController
@RequestMapping("/domain/category")
public class T01CategoryController extends BaseController<T01Category> {

  public T01CategoryController(IT01CategoryService service) {
    super(service);
  }

  /**
   * 查询所有或者分页查询
   * 提供page/size作为分页返回
   */
  @Override
  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(HttpServletRequest req, T01Category vo,
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
  public tech.wangbin.base.support.Resp insert(@RequestBody T01Category vo) {
    return super.insert(vo);
  }

  /**
   * 修改
   */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T01Category vo) {
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