package com.heaven.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_blog_tag")
public class TagBlogInfo {
    private Integer bId;
    private Integer tId;
}
