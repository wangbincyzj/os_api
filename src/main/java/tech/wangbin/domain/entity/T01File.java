package tech.wangbin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
// import tech.wangbin.base.support;
import com.baomidou.mybatisplus.annotation.IdType;
import tech.wangbin.base.support.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangBin
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T01File extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 文件名
   */
  private String name;

  /**
   * 拓展名
   */
  private String ext;

  private String src;

  /**
   * 父id
   */
  private Integer pid;

  private Long size;

  /**
   * 是否为目录
   */
  private Boolean isDir;

  /**
   * 密码
   */
  private String password;



  private String icon;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;


}
