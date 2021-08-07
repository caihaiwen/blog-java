package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.FriendLinkMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.FriendLinkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendLinkController {
    @Autowired
    private FriendLinkMapper friendLinkMapper;
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 列出出所有的友链信息
     * @Return: java.util.List<com.heaven.pojo.FriendLinkInfo>
     * @author: Heaven
     * @date: 2021/8/7 18:53
    */
    @CrossOrigin
    @GetMapping("/api/admin/listFriendLink")
    public Page<FriendLinkInfo> listLink(@RequestParam("currentPage") Integer currentPage,
                                         @RequestParam("pageSize") Integer pageSize){
        Page<FriendLinkInfo> IPage = new Page<>(currentPage,pageSize);
        friendLinkMapper.selectPage(IPage,null);
        return IPage;
    }
    /**
     * @Description: 通过Id来查找文章
     * @param: id: 文章Id
     * @Return: com.heaven.pojo.FriendLinkInfo
     * @author: Heaven
     * @date: 2021/8/7 20:38
    */
    @CrossOrigin
    @GetMapping("/api/admin/selectOneLink")
    public FriendLinkInfo selectOne(@RequestParam("id") Integer id){
        FriendLinkInfo friendLinkInfo = friendLinkMapper.selectById(id);
        return friendLinkInfo;
    }
    /**
     * @Description: 更新友链信息
     * @param: friendLinkInfo: 友链信息
     * @Return: java.lang.Boolean
     * @author: Heaven
     * @date: 2021/8/7 18:58
    */
    @CrossOrigin
    @PostMapping("/api/admin/updateFriendLink")
    public Boolean updateLink(@RequestBody FriendLinkInfo friendLinkInfo){
        AdminInfo adminInfo = adminMapper.selectOne(
                new QueryWrapper<AdminInfo>().eq("password", friendLinkInfo.getMd5Password()));
        if ( adminInfo != null){
            int update = friendLinkMapper.updateById(friendLinkInfo);
            if ( update == 1){
                return true;
            }
            else return false;
        }
        else return false;
    }
    /**
     * @Description: 通过Id来删除友链信息
     * @param: friendLinkInfo: 友链信息
     * @Return: java.lang.Boolean
     * @author: Heaven
     * @date: 2021/8/7 19:03
    */
    @CrossOrigin
    @PostMapping("/api/admin/deleteFriendLink")
    public Boolean deleteLink(@RequestBody FriendLinkInfo friendLinkInfo){
        AdminInfo adminInfo = adminMapper.selectOne(
                new QueryWrapper<AdminInfo>().eq("password", friendLinkInfo.getMd5Password()));
        if ( adminInfo != null){
            int delete = friendLinkMapper.deleteById(friendLinkInfo.getId());
            if ( delete == 1){
                return true;
            }
            else return false;
        }
        else return false;
    }
    /**
     * @Description: 插入友链信息
     * @param: friendLinkInfo: 友链信息
     * @Return: java.lang.Boolean
     * @author: Heaven
     * @date: 2021/8/7 19:06
    */
    @CrossOrigin
    @PostMapping("/api/admin/insertFriendLink")
    public Boolean insertLink(@RequestBody FriendLinkInfo friendLinkInfo){
        AdminInfo adminInfo = adminMapper.selectOne(
                new QueryWrapper<AdminInfo>().eq("password", friendLinkInfo.getMd5Password()));
        if ( adminInfo != null){
            int insert = friendLinkMapper.insert(friendLinkInfo);
            if ( insert == 1){
                return true;
            }
            else return false;
        }
        else return false;
    }
    /**
     * @Description: 查找所有的Id
     * @Return: java.util.List<com.heaven.pojo.FriendLinkInfo>
     * @author: Heaven
     * @date: 2021/8/7 20:39
    */
    @CrossOrigin
    @GetMapping("/api/getLink")
    public List<FriendLinkInfo> getLink(){
        return friendLinkMapper.selectList(null);
    }
}
