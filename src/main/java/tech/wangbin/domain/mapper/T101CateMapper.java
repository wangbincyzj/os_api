package tech.wangbin.domain.mapper;

import tech.wangbin.domain.entity.T101Cate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.wangbin.domain.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WangBin
 * @since 2021-07-14
 */
public interface T101CateMapper extends BaseMapper<T101Cate> {
  List<CategoryVo> getCategoryVo();
}
