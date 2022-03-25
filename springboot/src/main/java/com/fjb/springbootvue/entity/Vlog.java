package com.fjb.springbootvue.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/17/11:22
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_vlog")
public class Vlog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String avatarUrl;
    private String authorName;
    private String title;
    private String authorId;
    private Integer follow;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
    private String content;
    private Integer commentId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
