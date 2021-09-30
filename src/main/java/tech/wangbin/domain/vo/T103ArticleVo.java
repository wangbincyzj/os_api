package tech.wangbin.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.wangbin.domain.entity.T103Article;

@EqualsAndHashCode(callSuper = true)
@Data
public class T103ArticleVo extends T103Article {
  private int commentCount;
}
