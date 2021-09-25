package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "t_blog")
public class BlogInfo implements Serializable {
    @TableField(exist = false)
    private String md5Password; //md5加密后的密码
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; //主键，文章唯一标识
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private LocalDateTime releaseDate;   //发布时间
    private String title;   //文章标题
    private Boolean appreciate; //是否开启赞赏
    private String imageAddress;   //首图链接
    @TableField(exist = false)
    private String name;    //分类名称
    private Integer views;  //浏览量
    private String content; //文章内容
    private Integer sId;    //分类ID
    private Boolean published;  //是否发布
    private Boolean comment;    //是否开启评论
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private LocalDateTime updateDate;    //更新时间
    private Boolean isTop;  //是否置顶
    private String description; //描述消息
    @TableField(exist = false)
    private List<Integer> tags; //标签Id
    @TableField(exist = false)
    private List<TagInfo> tagsName; //标签信息
    @TableField(exist = false)
    private String year;
}
