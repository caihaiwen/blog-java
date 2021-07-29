package com.heaven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<BlogInfo> {
    IPage<BlogInfo> mListBlogs(Page<?> page,Integer state);
    List<BlogInfo> searchBlog(String info,Integer searchType);
}
