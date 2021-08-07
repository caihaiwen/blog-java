package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.MyInfoMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.MyInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyInfoController {
    @Autowired
    private MyInfoMapper myInfoMapper;
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 更新博客的设置信息，包括(备案号和日记)
     * @param: myInfo:
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/3 22:23
    */
    @CrossOrigin
    @PostMapping("/api/admin/updateSetting")
    public Boolean updateSetting(@RequestBody MyInfo myInfo){
        AdminInfo User = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", myInfo.getMd5Password()));
        if ( User != null){
            int update = myInfoMapper.updateById(myInfo);
            if ( update == 1){
                return true;
            }else{
                return false;
            }
        }
        else return false;
    }
    /**
     * @Description: 查询博客的信息包括备案号和日记
     * @Return: com.heaven.pojo.MyInfo
     * @author: heaven
     * @date: 2021/8/3 22:26
    */
    @CrossOrigin
    @GetMapping("/api/admin/selectInfo")
    public MyInfo selectInfo(){
        return myInfoMapper.selectById(1);
    }
    /**
     * @Description: 获取日记
     * @Return: java.lang.String
     * @author: heaven
     * @date: 2021/8/7 8:04
    */
    @CrossOrigin
    @GetMapping("/api/getSay")
    public String getSay(){
        return myInfoMapper.selectOne(new QueryWrapper<MyInfo>().eq("id",1)).getSay();
    }
}
