package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "t_tag")
@Data
public class TagInfo {
    @TableField(exist = false)
    private String md5Password;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String color;
    @TableField(exist = false)
    private Integer count;
}
