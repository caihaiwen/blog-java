package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_admin")
public class AdminInfo {
    private String user;
    private String password;
}
