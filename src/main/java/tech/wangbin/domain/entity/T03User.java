package tech.wangbin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.wangbin.base.support.UserEntity;

/**
 * @author WangBin
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T03User extends UserEntity {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String username;

  private String password;

  private String role;

  private String trueName;

  private String email;

  private String phone;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;


}
