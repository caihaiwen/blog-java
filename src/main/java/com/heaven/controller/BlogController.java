package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.*;
import com.heaven.pojo.*;
import com.heaven.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagBlogMapper tagBlogMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RedisUtil redisUtil;
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
                          @RequestParam("pageSize") Integer count){
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
        System.out.println(blogInfo);
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
    //--------------------------------------------
    /**
     * @Description: 在首页插叙中插叙所有的标题还有id用于搜索
     * @Return: java.util.List<com.heaven.pojo.BlogInfo>
     * @author: heaven
     * @date: 2021/8/4 11:59
    */
    @CrossOrigin
    @GetMapping("/api/searchAllInfo")
    public List<BlogInfo> searchSimpleBlog(){
        return blogMapper.selectList(new QueryWrapper<BlogInfo>().select("id", "title").eq("published",true));
    }
    @CrossOrigin
    @GetMapping("/api/listBlogs")
    public Page<BlogInfo> listBlog(@RequestParam("currentPage") Integer currentPage,
                                    @RequestParam("pageSize") Integer pageSize){
        Page<BlogInfo> IPage = new Page<>(currentPage,pageSize);
        blogMapper.selectPage(IPage,new QueryWrapper<BlogInfo>().orderByDesc("is_top").orderByAsc("release_date").eq("published",true));
        for (BlogInfo blog: IPage.getRecords()){
            TypeInfo typeInfo = typeMapper.selectById(blog.getSId());
            blog.setName(typeInfo.getName());
            List<TagBlogInfo> tagBlogInfos = tagBlogMapper.selectList(new QueryWrapper<TagBlogInfo>().eq("b_id", blog.getId()));
            List<TagInfo> tags = new ArrayList<>();
            for (TagBlogInfo tagBlogInfo: tagBlogInfos){
                TagInfo tagInfo = tagMapper.selectById(tagBlogInfo.getTId());
                tags.add(tagInfo);
            }
            blog.setTagsName(tags);
        }
        return IPage;
    }
    private void updateView(BlogInfo blogInfo){
        String id = String.valueOf(blogInfo.getId());
    }
    /**
     * @Description: 查找指定博客Id的内容
     * @param: id: 博客id
     * @Return: com.heaven.pojo.BlogInfo
     * @author: heaven
     * @date: 2021/8/6 9:30
    */
    @CrossOrigin
    @GetMapping("/api/searchOneBlogInfo")
    public BlogInfo OneBlogInfo(@RequestParam("id") String id){
        if ( redisUtil.exitHashKey("views",id) ){
            BlogInfo blogInfo = (BlogInfo) redisUtil.getHash("views", id);
            blogInfo.setViews(blogInfo.getViews()+1);
            redisUtil.setHash("views",id,blogInfo);
            return blogInfo;
        }
        else {
            BlogInfo blogInfo = blogMapper.selectById(id);
            blogInfo.setName(typeMapper.selectById(blogInfo.getSId()).getName());
            redisUtil.setHash("views", id, blogInfo);
            return blogInfo;
        }
    }
    /**
     * @Description: 查找文章,分类,标签的数量
     * @Return: java.util.Map<java.lang.String,java.lang.Integer>
     * @author: heaven
     * @date: 2021/8/6 9:31
    */
    @CrossOrigin
    @GetMapping("/api/listCount")
    public Map<String,Integer> listCount(){
        Integer pageCount = blogMapper.selectCount(new QueryWrapper<BlogInfo>().eq("published",true));
        Integer typeCount = typeMapper.selectCount(null);
        Integer tagCount = tagMapper.selectCount(null);
        Map<String,Integer> map = new HashMap<>();
        map.put("pageCount",pageCount);
        map.put("typeCount",typeCount);
        map.put("tagCount",tagCount);
        return map;
    }
    /**
     * @Description: 通过分类id来查找文章信息
     * @param: id: 分类Id
     * @param: currentPage: 当前页码
     * @param: pageSize: 页面大小
     * @Return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.heaven.pojo.BlogInfo>
     * @author: heaven
     * @date: 2021/8/7 13:12
    */
    @CrossOrigin
    @GetMapping("/api/type/listBlog")
    public Page<BlogInfo> listBlogByType(@RequestParam("sId") Integer id,
                                   @RequestParam("currentPage") Integer currentPage,
                                   @RequestParam("pageSize") Integer pageSize){
        Page<BlogInfo> IPage = new Page<>(currentPage,pageSize);
        blogMapper.selectPage(IPage,new QueryWrapper<BlogInfo>().orderByDesc("is_top").orderByAsc("release_date").eq("s_id",id).eq("published",true));
        for (BlogInfo blog: IPage.getRecords()){
            TypeInfo typeInfo = typeMapper.selectById(blog.getSId());
            blog.setName(typeInfo.getName());
            List<TagBlogInfo> tagBlogInfos = tagBlogMapper.selectList(new QueryWrapper<TagBlogInfo>().eq("b_id", blog.getId()));
            List<TagInfo> tags = new ArrayList<>();
            for (TagBlogInfo tagBlogInfo: tagBlogInfos){
                TagInfo tagInfo = tagMapper.selectById(tagBlogInfo.getTId());
                tags.add(tagInfo);
            }
            blog.setTagsName(tags);
        }
        return IPage;
    }
    /**
     * @Description: 根据标签Id来获取博客信息
     * @param: id: 标签Id
     * @param: currentPage: 当前页
     * @param: pageSize: 页大小
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @author: Heaven
     * @date: 2021/8/7 14:45
    */
    @CrossOrigin
    @GetMapping("/api/tag/listBlog")
    public Map<String,Object> listBlogByTag(@RequestParam("tId") Integer id,
                                        @RequestParam("currentPage") Integer currentPage,
                                        @RequestParam("pageSize") Integer pageSize){
        List<TagBlogInfo> tagBlogInfos = tagBlogMapper.selectList(new QueryWrapper<TagBlogInfo>().eq("t_id", id));
        Map<String,Object> map = new HashMap<>();
        if ( tagBlogInfos.size() % pageSize == 0){
            map.put("pages", tagBlogInfos.size() / pageSize);
        }else {
            map.put("pages", tagBlogInfos.size() / pageSize + 1);
        }
        List<BlogInfo> blogInfos = new ArrayList<>();
        for ( TagBlogInfo tagBlogInfo: tagBlogInfos){
            BlogInfo blog = blogMapper.selectOne(new QueryWrapper<BlogInfo>().eq("id", tagBlogInfo.getBId()).eq("published",true));
            if ( blog != null) {
                TypeInfo typeInfo = typeMapper.selectById(blog.getSId());
                blog.setName(typeInfo.getName());
                List<TagBlogInfo> tagBlogInfos1 = tagBlogMapper.selectList(new QueryWrapper<TagBlogInfo>().eq("b_id", blog.getId()));
                List<TagInfo> tags = new ArrayList<>();
                for (TagBlogInfo tagBlogInfo1 : tagBlogInfos1) {
                    TagInfo tagInfo = tagMapper.selectById(tagBlogInfo1.getTId());
                    tags.add(tagInfo);
                }
                blog.setTagsName(tags);
                blogInfos.add(blog);
            }
        }
        if ( pageSize*currentPage > tagBlogInfos.size()){
            List<BlogInfo> blogInfos1 = blogInfos.subList((currentPage - 1) * pageSize, tagBlogInfos.size() );
            map.put("records",blogInfos1);
        }
        else {
            List<BlogInfo> blogInfos1 = blogInfos.subList((currentPage - 1) * pageSize, pageSize * currentPage);
            map.put("records",blogInfos1);
        }
        return map;
    }
    /**
     * @Description: 按照年份来对博客信息进行排序
     * @Return: java.util.Map<java.lang.String,java.util.List>
     * @author: Heaven
     * @date: 2021/8/7 17:49
    */
    @CrossOrigin
    @GetMapping("/api/year/listBlog")
    public Map<String,List> listBlogByYear(){
        List<String> years = blogMapper.findYear();
        Map<String,List> map = new HashMap<>();
        for (String year : years){
            List<BlogInfo> blogInfos = blogMapper.findBlogByYear(year);
            map.put(year,blogInfos);
        }
        return map;
    }
    /**
     * @Description: 刷新过快返回的信息
     * @Return: java.lang.String
     * @author: Heaven
     * @date: 2021/8/10 19:22
    */
    @GetMapping("/flushQuickError")
    public String flushMax(){
        return "quick";
    }
}
