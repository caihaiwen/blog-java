package com.heaven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaven.mapper.AdminMapper;
import com.heaven.mapper.CommentMapper;
import com.heaven.pojo.AdminInfo;
import com.heaven.pojo.BlogInfo;
import com.heaven.pojo.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AdminMapper adminMapper;
    /**
     * @Description: 插入评论信息
     * @param: commentInfo:
     * @Return: java.lang.Boolean
     * @author: Heaven
     * @date: 2021/8/8 15:11
    */
    @CrossOrigin
    @PostMapping("/api/insertComment")
    public Boolean insertComment(@RequestBody CommentInfo commentInfo){
        if ( adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password",commentInfo.getMd5Password())) != null){
            commentInfo.setPublished(true);
            commentInfo.setIsAdmin(true);
            commentInfo.setName("Heaven");
        }
        int insert = commentMapper.insert(commentInfo);
        if ( insert == 1){
            return true;
        }
        return false;
    }
    /**
     * @Description: 通过文章Id来获取
     * @param: id: 文章Id
     * @Return: java.util.Map<java.lang.Integer,java.util.List<com.heaven.pojo.CommentInfo>>
     * @author: Heaven
     * @date: 2021/8/10 11:29
    */
    @CrossOrigin
    @GetMapping("/api/getComment")
    public Map<Integer, List<CommentInfo>> getComment(@RequestParam("bId") Integer id)  {
        List<CommentInfo> PComments = commentMapper.selectList(new QueryWrapper<CommentInfo>().eq("parent_id", -1).eq("b_id",id).orderByDesc("c_date").eq("published",true));
        Map<Integer,List<CommentInfo>> map = new HashMap<>();
        Integer i = 0;
        for ( CommentInfo pComment: PComments){
            List<CommentInfo> commentInfos = commentMapper.selectList(new QueryWrapper<CommentInfo>().eq("parent_id", pComment.getId()).eq("b_id",id).orderByDesc("c_date").eq("published",true));
            commentInfos.add(0,pComment);
            map.put(i,commentInfos);
            i++;
        }
        return map;
    }
    /**
     * @Description: 根据请求方式来获取评论
     * @param: method: 为空则表示获取两个已审核和未审核的评论,1则表示获取未审核的评论,2则表示获取已审核的评论
    * @param: current: 当前也
    * @param: size: 页大小
     * @Return: com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author: Heaven
     * @date: 2021/8/15 20:24
    */
    @CrossOrigin
    @GetMapping("/api/admin/getComment")
    public Page getComments(@RequestParam("method") Integer method,
                            @RequestParam("current") Integer current,
                            @RequestParam("pageSize") Integer size){
        Page<CommentInfo> page = new Page<>(current,size);
        if ( method == 3){
            commentMapper.selectPage(page,new QueryWrapper<CommentInfo>().orderByAsc("c_date"));
        }
        //查找未审核的评论
        else if (method == 1){
            commentMapper.selectPage(page,new QueryWrapper<CommentInfo>().eq("published",false).orderByAsc("c_date"));
        }
        else if ( method == 2){
            commentMapper.selectPage(page,new QueryWrapper<CommentInfo>().eq("published",true).orderByAsc("c_date"));
        }
        return page;
    }
    /**
     * @Description: 更新评论信息
     * @param: commentInfo: 评论信息
     * @Return: java.lang.Boolean 返回值，如果修改成功返回true,否则返回false
     * @author: Heaven
     * @date: 2021/8/15 20:58
    */
    @CrossOrigin
    @PostMapping("/api/admin/updateComment")
    public Boolean updateComment(@RequestBody CommentInfo commentInfo){
        AdminInfo adminInfo = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password", commentInfo.getMd5Password()));
        if ( adminInfo == null){
            return false;
        }
        int i = commentMapper.updateById(commentInfo);
        if ( i == 1){
            return true;
        }
        else return false;
    }
    @CrossOrigin
    @PostMapping("/api/admin/deleteComment")
    public Boolean deleteComment(@RequestBody CommentInfo commentInfo){
        AdminInfo adminInfo = adminMapper.selectOne(new QueryWrapper<AdminInfo>().eq("password",commentInfo.getMd5Password()));
        if ( adminInfo == null){
            return false;
        }
        int delete = commentMapper.delete(new QueryWrapper<CommentInfo>().eq("id", commentInfo.getId()).or().eq("parent_id", commentInfo.getId()));
        if ( delete != 0){
            return true;
        }
        else return false;
    }
}
