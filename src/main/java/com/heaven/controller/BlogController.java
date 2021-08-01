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
    /**
     * @Description: 列出文章详细信息
     * @param: currentPage: //当前页
     * @param: count:   //当前页数
     * @Return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author: heaven
     * @date: 2021/8/1 14:17
    */
    @CrossOrigin
    @GetMapping("/api/admin/listPages")
    public Page listBlogs(@RequestParam("currentPage") Integer currentPage,
                          @RequestParam("count") Integer count){
        Page<BlogInfo> iPage = new Page<>(currentPage,count);
        blogMapper.mListBlogs(iPage,null);
        return iPage;
    }
    /**
     * @Description: 搜索文章
     * @param: info:    文章标题
     * @param: searchType:   文章分类
     * @Return: java.util.List<com.heaven.pojo.BlogInfo>
     * @author: heaven
     * @date: 2021/8/1 14:17
    */
    @CrossOrigin
    @GetMapping("/api/admin/searchPage")
    public List<BlogInfo> searchBlog(@RequestParam("info") String info,
                                     @RequestParam("searchType") Integer searchType){
        return blogMapper.searchBlog(info, searchType);
    }
    /**
     * @Description: 携带参数跳转到文章编辑界面
     * @param: id: 文章的唯一ID
     * @Return: com.heaven.pojo.BlogInfo
     * @author: heaven
     * @date: 2021/8/1 14:24
    */
    @CrossOrigin
    @GetMapping("/api/admin/editorPage")
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
    /**
     * @Description: 更新文章
     * @param: blogInfo: 文章信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:25
    */
    @CrossOrigin
    @PostMapping("/api/admin/updatePage")
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
    /**
     * @Description: 通过文章ID删除文章，同时也删除文章的标签
     * @param: blogInfo: 文章信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:27
    */
    @CrossOrigin
    @PostMapping("/api/admin/deletePage")
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
    /**
     * @Description: 添加文章
     * @param: blogInfo: 文章信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:28
    */
    @CrossOrigin
    @PostMapping("/api/admin/addPage")
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
