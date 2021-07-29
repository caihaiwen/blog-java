package com.heaven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaven.pojo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<AdminInfo> {
}
