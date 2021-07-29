package com.heaven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.pojo.TypeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<TypeInfo> {
    IPage<TypeInfo> listTypes(Page<?> page,Integer state);
    List<TypeInfo> searchTypes(String searchInfo);
}
