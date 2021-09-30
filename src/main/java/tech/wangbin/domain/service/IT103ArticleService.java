package tech.wangbin.domain.service;

import tech.wangbin.domain.entity.T103Article;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.wangbin.domain.vo.T103ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WangBin
 * @since 2021-07-15
 */
public interface IT103ArticleService extends IService<T103Article> {
  List<T103ArticleVo> getArticleVoList();
}
