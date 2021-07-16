package tech.wangbin.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
// import tech.wangbin.base.support;
import tech.wangbin.base.support.BaseEntity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangBin
 * @since 2021-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T101Cate extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String name;

  private String brief;

  private String img;

  private Integer rank;


  private Integer pId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;


}
