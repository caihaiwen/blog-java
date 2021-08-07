package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_info")
public class MyInfo {
    @TableId(value = "id")
    private Integer id;
    private String recordNum;
    private String say;
    @TableField(exist = false)
    private String md5Password;
}
