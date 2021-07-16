package tech.wangbin.domain.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import tech.wangbin.domain.service.IT101CateService;
import tech.wangbin.domain.entity.T101Cate;
import tech.wangbin.base.support.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
* @author WangBin
* @since 2021-07-14
*/
@Slf4j
@RestController
@RequestMapping("/domain/cate")
public class T101CateController extends BaseController<T101Cate> {

  @Autowired
  private IT101CateService service;

  public T101CateController(IT101CateService service) {
    super(service);
  }


  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(){
    return Resp.ok(service.getCategoryList());
  }




  @Override
  @PutMapping("/updateBatch")
  public Resp updateBatch(@RequestBody List<T101Cate> t) {
    return super.updateBatch(t);
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
  public tech.wangbin.base.support.Resp insert(@RequestBody T101Cate vo) {
    return super.insert(vo);
  }

  /**
  * 修改
  */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T101Cate vo) {
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



























