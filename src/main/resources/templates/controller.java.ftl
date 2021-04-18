package ${package.Controller};

import lombok.extern.slf4j.Slf4j;

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${cfg.controllerPackage}.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
* @author ${author}
* @since ${date}
*/
@Slf4j
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerName} extends ${superControllerClass}<${entity}> {

  public ${table.controllerName}(${table.serviceName} service) {
    super(service);
  }

  /**
  * 查询所有或者分页查询
  * 提供page/size作为分页返回
  */
  @Override
  @GetMapping(value = "")
  public ${cfg.responseName} getAll(HttpServletRequest req, ${entity} vo,
    @RequestParam(value = "asc", required = false) List<String> asc,
    @RequestParam(value = "desc", required = false) List<String> desc) {
    return super.getAll(req, vo, asc, desc);
  }



  /**
  * 通过主键查询单条数据
  */
  @Override
  @GetMapping(value = "/{id}")
  public ${cfg.responseName} getById(@PathVariable Integer id) {
    return super.getById(id);
  }

  /**
  * 新增
  */
  @Override
  @PostMapping(value = "")
  public ${cfg.responseName} insert(@RequestBody ${entity} vo) {
    return super.insert(vo);
  }

  /**
  * 修改
  */
  @Override
  @PutMapping(value = "")
  public ${cfg.responseName} update(@RequestBody ${entity} vo) {
    return super.update(vo);
  }

  /**
  * 删除
  */
  @Override
  @DeleteMapping(value = "/{id}")
  public ${cfg.responseName} delete(@PathVariable Integer id) {
    return super.delete(id);
  }
}