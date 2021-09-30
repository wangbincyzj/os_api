package tech.wangbin.domain.service.impl;

import tech.wangbin.domain.entity.T103Article;
import tech.wangbin.domain.mapper.T103ArticleMapper;
import tech.wangbin.domain.service.IT103ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.wangbin.domain.vo.T103ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WangBin
 * @since 2021-07-15
 */
@Service
public class T103ArticleServiceImpl extends ServiceImpl<T103ArticleMapper, T103Article> implements IT103ArticleService {

  @Override
  public List<T103ArticleVo> getArticleVoList() {
    return baseMapper.getArticleList();
  }
}
