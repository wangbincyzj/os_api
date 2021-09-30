package tech.wangbin.domain.service.impl;

import tech.wangbin.domain.entity.T101Cate;
import tech.wangbin.domain.mapper.T101CateMapper;
import tech.wangbin.domain.service.IT101CateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.wangbin.domain.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WangBin
 * @since 2021-07-14
 */
@Service
public class T101CateServiceImpl extends ServiceImpl<T101CateMapper, T101Cate> implements IT101CateService {

  @Override
  public List<CategoryVo> getCategoryList() {
    return baseMapper.getCategoryVo();
  }

  @Override
  public CategoryVo getCategoryVoById(int id) {
    return baseMapper.getCategoryVoById(id);
  }
}
