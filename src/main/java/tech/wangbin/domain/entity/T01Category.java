package tech.wangbin.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.wangbin.base.support.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author WangBin
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T01Category extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String name;

  private String subName;

  private Integer parentId;

  private Integer count;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;
}
