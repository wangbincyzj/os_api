package tech.wangbin.domain.service.impl;

import io.minio.errors.ErrorResponseException;
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

/**
 * <p>
 *  服务实现类
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
    }catch (Exception e){
      minioUtil.makeBucket(bucketName);
      try {
        minioUtil.putObject(bucketName, file, fileName);
      }catch (Exception e2){
        return e2.toString();
      }
    }
    return null;
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
