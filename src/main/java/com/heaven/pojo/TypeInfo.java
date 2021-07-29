package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_sort")
public class TypeInfo {
    @TableField(exist = false)
    private String md5Password;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(exist = false)
    private Integer count;
    private String color;
}
