package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_friendlink")
public class FriendLinkInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String imgLink;
    private String name;
    private String toLink;
    private String description;
    @TableField(exist = false)
    private String md5Password;
}
