package tech.wangbin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
// import tech.wangbin.base.support;
import com.baomidou.mybatisplus.annotation.IdType;
import tech.wangbin.base.support.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangBin
 * @since 2021-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T103Article extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String title;

  /**
   * html文本
   */
  private String html;

  /**
   * 原始数据
   */
  private String raw;

  /**
   * 0:md;1:富文本
   */
  private Integer editType;


  /**
   * 0公开 1私密 2粉丝课件
   */
  private Integer showType;

  /**
   * 用户id
   */
  private Integer uId;

  /**
   * 分类id
   */
  private Integer cId;

  /**
   * 是否置顶
   */
  private Boolean top;

  /**
   * 排序
   */
  private Integer rank;

  /**
   * 阅读
   */
  private Integer readCount;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;


}
