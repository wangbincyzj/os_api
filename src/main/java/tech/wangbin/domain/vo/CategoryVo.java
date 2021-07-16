package tech.wangbin.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.wangbin.domain.entity.T101Cate;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryVo extends T101Cate implements Serializable {
  private Integer articleCount;
}
