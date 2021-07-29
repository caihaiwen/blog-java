package com.heaven.controller;

import com.heaven.mapper.TagBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagBlogController {
    @Autowired
    private TagBlogMapper tagBlogMapper;
}
