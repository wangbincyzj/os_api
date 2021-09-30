package tech.wangbin.domain.mapper;

import tech.wangbin.domain.entity.T103Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.wangbin.domain.vo.T103ArticleVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WangBin
 * @since 2021-07-15
 */
public interface T103ArticleMapper extends BaseMapper<T103Article> {
  List<T103ArticleVo> getArticleList();
}
