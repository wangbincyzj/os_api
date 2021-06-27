package tech.wangbin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
// import tech.wangbin.base.support;
import tech.wangbin.base.support.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
    * @author WangBin
    * @since 2021-06-27
*/
    @Data
    @EqualsAndHashCode(callSuper = true)
    public class T04MnSinger extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String desc;

    private String imgS;

    private String imgM;

    private String imgL;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDelete;

    private Integer createBy;


}
