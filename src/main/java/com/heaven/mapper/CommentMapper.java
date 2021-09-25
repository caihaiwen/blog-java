package com.heaven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaven.pojo.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<CommentInfo> {
}
