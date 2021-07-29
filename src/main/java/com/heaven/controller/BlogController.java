package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.BlogMapper;
import com.heaven.mapper.TagBlogMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.BlogInfo;
import com.heaven.pojo.TagBlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagBlogMapper tagBlogMapper;
    @Autowired
    private AdminMapper adminMapper;
    @CrossOrigin
    @GetMapping("/api/admin/listBlogs")
    public Page listBlogs(@RequestParam("currentPage") Integer currentPage,
                          @RequestParam("count") Integer count){
        Page<BlogInfo> iPage = new Page<>(currentPage,count);
        blogMapper.mListBlogs(iPage,null);
        return iPage;
    }
    @CrossOrigin
    @GetMapping("/api/admin/searchBlog")
    public List<BlogInfo> searchBlog(@RequestParam("info") String info,
                                     @RequestParam("searchType") Integer searchType){
        return blogMapper.searchBlog(info, searchType);
    }
    @CrossOrigin
    @GetMapping("/api/admin/Editor")
    public BlogInfo Editor(@RequestParam("id") Integer id){
        List<TagBlogInfo> tagbloginfos = tagBlogMapper.selectList(new QueryWrapper<TagBlogInfo>().eq("b_id", id));
        BlogInfo blogInfo = blogMapper.selectById(id);
        if ( tagbloginfos != null) {
            ArrayList<Integer> tags = new ArrayList<>();
            for (TagBlogInfo tagBlogInfo : tagbloginfos) {
                tags.add(tagBlogInfo.getTId());
            }
            blogInfo.setTags(tags);
        }
        return blogInfo;
    }
    @CrossOrigin
    @PostMapping("/api/admin/pages/update")
    public Boolean UpdatePage(@RequestBody BlogInfo blogInfo){
        AdminInfo password = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", blogInfo.getMd5Password()));
        blogInfo.setUpdateDate(null);
        int id = blogInfo.getId();
        if ( password != null){
            blogMapper.updateById(blogInfo);
            tagBlogMapper.delete(new QueryWrapper<TagBlogInfo>().eq("b_id",id));
            for ( Integer tags: blogInfo.getTags()){
                tagBlogMapper.insert(new TagBlogInfo(id,tags));
            }
            return true;
        }
        else {
            return false;
        }
    }
    @CrossOrigin
    @PostMapping("/api/admin/page/delete")
    public Boolean deletePage(@RequestBody BlogInfo blogInfo){
        AdminInfo password = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", blogInfo.getMd5Password()));
        if ( password != null){
            blogMapper.deleteById(blogInfo.getId());
            tagBlogMapper.delete(new QueryWrapper<TagBlogInfo>().eq("b_id",blogInfo.getId()));
            return true;
        }
        else
            return false;
    }
    @CrossOrigin
    @PostMapping("/api/admin/page/add")
    public Boolean addPage(@RequestBody BlogInfo blogInfo){
        int insert = blogMapper.insert(blogInfo);
        Integer id = blogInfo.getId();
        if ( insert == 1){
            for (Integer tag : blogInfo.getTags()){
                tagBlogMapper.insert(new TagBlogInfo(id,tag));
            }
            return true;
        }
        else
            return false;
    }
}
