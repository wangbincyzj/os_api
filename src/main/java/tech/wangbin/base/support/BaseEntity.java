package tech.wangbin.base.support;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

  @TableLogic
  private Boolean isDelete;
}
