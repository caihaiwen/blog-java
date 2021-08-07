package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.TagMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 查找所有的标签(不分页)
     * @Return: java.util.List<com.heaven.pojo.TagInfo>
     * @author: heaven
     * @date: 2021/8/1 14:54
    */
    @CrossOrigin
    @GetMapping("/api/admin/noPage/listTags")
    public List<TagInfo> listTag(){
        return tagMapper.selectList(null);
    }
    /**
     * @Description: 查找所有的标签
     * @param: currentPage: 当前页
     * @param: pageSize: 当前页大小
     * @Return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author: heaven
     * @date: 2021/8/1 14:53
    */
    @CrossOrigin
    @GetMapping("/api/admin/listTags")
    public Page listTags(@RequestParam("currentPage") Integer currentPage,
                                  @RequestParam("pageSize") Integer pageSize){
        Page<TagInfo> iPage = new Page<>(currentPage,pageSize);
        tagMapper.listTags(iPage,null);
        return iPage;
    }
    /**
     * @Description: 根据ID查找标签
     * @param: id: 当前标签的ID
     * @Return: com.heaven.pojo.TagInfo
     * @author: heaven
     * @date: 2021/8/1 14:55
    */
    @CrossOrigin
    @GetMapping("/api/admin/selectOneTag")
    public TagInfo editorTag(@RequestParam("id") Integer id){
        return tagMapper.selectById(id);
    }

    /**
     * @Description: 根据标签名称来查询标签的信息
     * @param: searchInfo: 标签名称
     * @Return: java.util.List<com.heaven.pojo.TagInfo>
     * @author: heaven
     * @date: 2021/8/1 15:03
    */
    @CrossOrigin
    @GetMapping("/api/admin/searchTags")
    public List<TagInfo> searchTags(@RequestParam("searchInfo") String searchInfo){
        return tagMapper.searchTags(searchInfo);
    }
    /**
     * @Description: 更新标签的信息
     * @param: tagInfo: 标签信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 15:04
    */
    @CrossOrigin
    @PostMapping("/api/admin/updateTag")
    public Boolean updateTag(@RequestBody TagInfo tagInfo){
        AdminInfo User = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", tagInfo.getMd5Password()));
        if ( User != null){
            tagMapper.updateById(tagInfo);
            return true;
        }
        else return false;
    }
    /**
     * @Description: 添加标签
     * @param: tagInfo: 标签信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 15:08
    */
    @CrossOrigin
    @PostMapping("/api/admin/addTag")
    public Boolean addTags(@RequestBody TagInfo tagInfo){
        AdminInfo User = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", tagInfo.getMd5Password()));
        if ( User != null){
            tagMapper.insert(tagInfo);
            return true;
        }
        else
            return false;
    }
    /**
     * @Description: 根据标签Id来删除标签
     * @param: tagInfo: 标签信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 15:15
    */
    @CrossOrigin
    @PostMapping("/api/admin/deleteTag")
    public Boolean deleteTag(@RequestBody TagInfo tagInfo){
        AdminInfo User = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", tagInfo.getMd5Password()));
        if ( User != null){
            tagMapper.deleteById(tagInfo.getId());
            return true;
        }
        else return false;
    }
    /**
     * @Description: 获取所有的标签信息
     * @Return: java.util.List<com.heaven.pojo.TagInfo>
     * @author: Heaven
     * @date: 2021/8/7 8:21
    */
    @CrossOrigin
    @GetMapping("/api/listTag")
    public List<TagInfo> listTags(){
        return tagMapper.selectList(null);
    }
}
