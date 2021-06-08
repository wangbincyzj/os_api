package tech.wangbin.domain.service;

import org.springframework.web.multipart.MultipartFile;
import tech.wangbin.domain.entity.T01File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WangBin
 * @since 2021-06-08
 */
public interface IT01FileService extends IService<T01File> {
  String newFIle(String bucketName, String fileName, MultipartFile file);

  InputStream getFileStream(String bucketName, String fileName);

  String getFileUrl(String bucketName, String fileName);
}
