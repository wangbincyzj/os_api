package tech.wangbin.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import tech.wangbin.domain.entity.T01File;
import tech.wangbin.domain.mapper.T01FileMapper;
import tech.wangbin.domain.service.IT01FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.wangbin.domain.utils.MinioUtil;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WangBin
 * @since 2021-06-08
 */
@Service
@Slf4j
public class T01FileServiceImpl extends ServiceImpl<T01FileMapper, T01File> implements IT01FileService {
  @Resource
  private MinioUtil minioUtil;


  @Override
  public String newFIle(String bucketName, String fileName, MultipartFile file) {
    try {
      minioUtil.putObject(bucketName, file, fileName);
    } catch (Exception e) {
      minioUtil.makeBucket(bucketName);
      try {
        minioUtil.putObject(bucketName, file, fileName);
      } catch (Exception e2) {
        return e2.toString();
      }
    }
    return null;
  }

  @Override
  public T01File newFIle(MultipartFile file, Map<String, String> map) {
    // 生产时间戳值
    String src = map.get("src");
    String ext = map.get("ext");
    String name = map.get("name");
    String pid = map.get("pid");
    String bucketName = ext.length() < 3 ? ext + "00" : ext;
    String hash, errMsg = null;
    boolean isUpdate = false;
    if (src == null) {  // 新文件->insert
      hash = String.valueOf(new Date().getTime());
      src = name + "-" + ext + "-" + hash;
    } else {  // 旧文件->update
      isUpdate = true;
      String[] strings = src.split("-");
      name = strings[0];
      ext = strings[1];
      hash = strings[2];
    }
    if (map.get("id") != null) {
      isUpdate = true;
    }
    try {
      minioUtil.putObject(bucketName, file, name + "/" + hash);
    } catch (Exception e) {
      minioUtil.makeBucket(bucketName);
      try {
        minioUtil.putObject(bucketName, file, name + "/" + hash);
      } catch (Exception e2) {
        errMsg = e2.toString();
      }
    }
    if (errMsg == null) {
      T01File newFile = new T01File();
      newFile.setName(name);
      newFile.setExt(ext);
      newFile.setSrc(src);
      newFile.setIsDir(false);
      newFile.setSize(file.getSize());
      newFile.setPid(Integer.valueOf(pid));
      newFile.setIcon(map.get("icon"));
      if (isUpdate) {
        newFile.setId(Integer.valueOf(map.get("id")));
      }
      return newFile;
    } else {
      return null;
    }
  }

  @Override
  public InputStream getFileStream(String bucketName, String fileName) {
    return minioUtil.getObject(bucketName, fileName);
  }

  @Override
  public String getFileUrl(String bucketName, String fileName) {
    return minioUtil.getObjectUrl(bucketName, fileName);
  }
}
