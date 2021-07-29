package com.heaven.controller;

import com.heaven.mapper.TagMapper;
import com.heaven.pojo.TagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    private TagMapper tagMapper;
    /**
     * @Description: TODO
     返回用户需要的列表
     * @Return: java.util.List<com.heaven.pojo.TagInfo>
     * @author: Heaven
     * @date: 2021/7/29 10:17
    */
    @CrossOrigin
    @GetMapping("/api/admin/listTag")
    public List<TagInfo> listTag(){
        return tagMapper.selectList(null);
    }
}
