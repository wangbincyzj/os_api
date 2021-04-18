package tech.wangbin.base.support;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class BaseController<T extends BaseEntity> {

  public IService<T> baseService;

  public BaseController(IService<T> baseService) {
    this.baseService = baseService;
  }

  public Resp getAll(HttpServletRequest req, T t, List<String> asc, List<String> desc) {
    System.out.println(asc);
    System.out.println(desc);
    // 通过request判断是否有分页数据
    IPage<T> iPage = (IPage<T>) PagerUtil.parse(req, t.getClass());
    QueryWrapper<T> qw = new QueryWrapper<>(t);
    if (asc != null) {
      asc.forEach(s -> qw.orderByAsc(s.toString()));
    }
    if (desc != null) {
      desc.forEach(s -> qw.orderByDesc(s.toString()));
    }
    Object ret;
    if (iPage != null) {
      ret = baseService.page(iPage, qw);
    } else {
      ret = baseService.list(qw);
    }
    return Resp.ok(ret);
  }

  public Resp getById(Integer id) {
    return Resp.ok(baseService.getById(id));
  }

  public Resp insert(T t) {
    t.setCreateTime(LocalDateTime.now());
    return Resp.ok(baseService.save(t));
  }

  public Resp update(T t){
    t.setUpdateTime(LocalDateTime.now());
    return Resp.ok(baseService.updateById(t));
  }

  public Resp delete(Integer id){
    return Resp.ok(baseService.removeById(id));
  }
}
