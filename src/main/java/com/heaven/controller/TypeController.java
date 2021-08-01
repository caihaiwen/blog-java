package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.TypeMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.BlogInfo;
import com.heaven.pojo.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 查找分类信息(不分页)
     * @Return: java.util.List<com.heaven.pojo.TypeInfo>
     * @author: heaven
     * @date: 2021/8/1 14:29
    */
    @CrossOrigin
    @GetMapping("/api/admin/noPage/listTypes")
    public List<TypeInfo> noPageListTypes(){
        List<TypeInfo> typeInfos = typeMapper.selectList(null);
        return typeInfos;
    }
    /**
     * @Description: 以分页的方式查找信息
     * @param: currentPage: 当前页
     * @param: pageSize: 当前页大小
     * @Return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author: heaven
     * @date: 2021/8/1 14:30
    */
    @CrossOrigin
    @GetMapping("/api/admin/listTypes")
    public Page ListTypes(@RequestParam("currentPage") Integer currentPage,
                          @RequestParam("pageSize") Integer pageSize){
        Page<TypeInfo> iPage = new Page<>(currentPage,pageSize);
        typeMapper.listTypes(iPage,null);
        return iPage;
    }
    /**
     * @Description: 以分类名来搜索分类
     * @param: searchInfo: 分类名
     * @Return: java.util.List<com.heaven.pojo.TypeInfo>
     * @author: heaven
     * @date: 2021/8/1 14:31
    */
    @CrossOrigin
    @GetMapping("/api/admin/searchType")
    public List<TypeInfo> searchType(@RequestParam("searchInfo") String searchInfo){
        return typeMapper.searchTypes(searchInfo);
    }
    /**
     * @Description: 获取当前分类的信息
     * @param: id: 分类ID
     * @Return: com.heaven.pojo.TypeInfo
     * @author: heaven
     * @date: 2021/8/1 14:31
    */
    @CrossOrigin
    @GetMapping("/api/admin/selectOneType")
    public TypeInfo selectOne(@RequestParam("id") Integer id){
        return typeMapper.selectById(id);
    }
    /**
     * @Description: 更新分类信息
     * @param: typeInfo: 分类信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:32
    */
    @CrossOrigin
    @PostMapping("/api/admin/updateType")
    public Boolean updateType(@RequestBody TypeInfo typeInfo){
        AdminInfo target = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", typeInfo.getMd5Password()));
        if ( target != null) {
            int i = typeMapper.updateById(typeInfo);
            if (i == 1) {
                return true;
            } else
                return false;
        }
        else
            return false;
    }
    /**
     * @Description: 添加分类
     * @param: typeInfo: 分类信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:32
    */
    @CrossOrigin
    @PostMapping("/api/admin/insertType")
    public Boolean insertType(@RequestBody TypeInfo typeInfo){
        AdminInfo target = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", typeInfo.getMd5Password()));
        if ( target != null) {
            int i = typeMapper.insert(typeInfo);
            if (i == 1) {
                return true;
            } else
                return false;
        }
        else
            return false;
    }
    /**
     * @Description: 删除该分类
     * @param: typeInfo: 分类信息
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:32
    */
    @CrossOrigin
    @PostMapping("/api/admin/deleteType")
    public Boolean deleteType(@RequestBody TypeInfo typeInfo){
        AdminInfo target = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", typeInfo.getMd5Password()));
        if ( target != null){
            int i = typeMapper.deleteById(typeInfo.getId());
            if ( i == 1){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
