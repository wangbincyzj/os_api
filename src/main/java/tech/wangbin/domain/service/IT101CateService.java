package tech.wangbin.domain.service;

import tech.wangbin.domain.entity.T101Cate;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.wangbin.domain.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WangBin
 * @since 2021-07-14
 */
public interface IT101CateService extends IService<T101Cate> {
  List<CategoryVo> getCategoryList();

  CategoryVo getCategoryVoById(int id);
}
