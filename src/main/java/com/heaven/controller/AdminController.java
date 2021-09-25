package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heaven.mapper.AdminMapper;
import com.heaven.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 登录验证
     * @param: adminInfo:
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:12
    */
    @CrossOrigin
    @PostMapping("/api/admin/verity")
    public Boolean Verity(@RequestBody AdminInfo adminInfo){
        AdminInfo adminInfo1 = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("user",adminInfo.getUser()));
        if ( adminInfo1 == null){
            return false;
        }else {
            if ( adminInfo1.getPassword().equals(adminInfo.getPassword())){
                return true;
            }else{
                return false;
            }
        }
    }
    /**
     * @Description: 密码修改
     * @param: adminInfo:
     * @Return: java.lang.Boolean
     * @author: heaven
     * @date: 2021/8/1 14:13
    */
    @CrossOrigin
    @PostMapping("/api/admin/modify")
    public Boolean Modify(@RequestBody AdminInfo adminInfo){
        int count = adminMapper.update(adminInfo,
                new QueryWrapper<AdminInfo>().eq("user", adminInfo.getUser()));
        if ( count == 1){
            return true;
        }else
            return false;
    }
    /**
     * @Description: 判断是否登录过
     * @param: adminInfo: 验证信息
     * @Return: java.lang.Boolean
     * @author: Heaven
     * @date: 2021/8/16 8:42
    */
    @CrossOrigin
    @PostMapping("/api/isAdmin")
    public Boolean isAdmin(@RequestBody AdminInfo adminInfo){
        AdminInfo adminInfo1 = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", adminInfo.getPassword()));
        if ( adminInfo1 != null){
            return true;
        }
        else return false;
    }
}
