package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "t_comment")
public class CommentInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String email;
    @TableField( value = "c_date",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime cdate;
    @TableField(value = "b_id")
    private Integer bId;
    private Integer parentId;
    private String content;
    private String rName;
    private Boolean published;
    private Boolean isAdmin;
    @TableField(exist = false)
    private String md5Password;
}
