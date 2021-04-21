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
    * @since 2021-04-20
*/
    @Data
    @EqualsAndHashCode(callSuper = true)
    public class T02Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String mdContent;

    private String htmlContent;

    private String cover;

    private String author;

            /**
            * 分类
            */
    private Integer categoryId;

            /**
            * 标签s
            */
    private String tagsId;

            /**
            * 密码
            */
    private String password;

    private Integer commentCount;

    private Boolean commentStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDelete;


}
