package tech.wangbin.domain.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import tech.wangbin.domain.service.IT01FileService;
import tech.wangbin.domain.entity.T01File;
import tech.wangbin.base.support.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tech.wangbin.domain.utils.MinioUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author WangBin
 * @since 2021-06-08
 */
@Slf4j
@RestController
@RequestMapping("/domain/file")
public class T01FileController extends BaseController<T01File> {
  @Resource
  private IT01FileService service;

  public T01FileController(IT01FileService service) {
    super(service);
  }

  /**
   * 查询所有或者分页查询
   * 提供page/size作为分页返回
   */
  @Override
  @GetMapping(value = "")
  public tech.wangbin.base.support.Resp getAll(HttpServletRequest req, T01File vo,
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
  public tech.wangbin.base.support.Resp insert(@RequestBody T01File vo) {
    return super.insert(vo);
  }

  /**
   * 修改
   */
  @Override
  @PutMapping(value = "")
  public tech.wangbin.base.support.Resp update(@RequestBody T01File vo) {
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


  @PostMapping("/newFile")
  public Resp newFile(MultipartFile file, @RequestParam Map<String, String> map) {
    // 生产时间戳值
    String src = map.get("src");
    String ext = map.get("ext");
    String name = map.get("name");
    String pid = map.get("pid");
    String bucketName;
    String hash;
    if (src == null) {
      hash = String.valueOf(new Date().getTime());
      src = name + "-" + ext + "-" + hash;
    }else{
      String[] strings = src.split("-");
      name = strings[0];
      ext = strings[1];
      hash = strings[2];
    }
    if(ext.length() < 3){
      bucketName = ext + "00";
    }else {
      bucketName = ext;
    }
    String errMsg = service.newFIle(bucketName, name + "/" + hash, file);
    if (errMsg == null) {
      T01File newFile = new T01File();
      newFile.setName(name);
      newFile.setExt(ext);
      newFile.setSrc(src);
      newFile.setIsDir(false);
      newFile.setSize(file.getSize());
      newFile.setPid(Integer.valueOf(pid));
      return this.insert(newFile);
    } else {
      return Resp.err(errMsg);
    }
  }

  @GetMapping("/fileStream/{src}")
  public void getFileStream(@PathVariable("src") String src, HttpServletResponse resp) throws IOException {
    String[] strings = src.split("-");
    String name = strings[0];
    String ext = strings[1];
    String hash = strings[2];
    InputStream fileStream = service.getFileStream(ext, name + "/" + hash);
    StreamUtils.copy(fileStream, resp.getOutputStream());
  }

  @GetMapping("/fileUrl/{src}")
  public Resp getFileUrl(@PathVariable("src") String src, HttpServletResponse resp) {
    String[] strings = src.split("-");
    String name = strings[0];
    String ext = strings[1];
    String hash = strings[2];
    String url = service.getFileUrl(ext, name + "/" + hash);
    return Resp.ok(url);
  }
}