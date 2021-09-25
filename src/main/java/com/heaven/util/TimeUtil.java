package com.heaven.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.heaven.mapper.BlogMapper;
import com.heaven.pojo.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @Description: 定时器（定时将redis数据同步到mysql
 * @param: null:
 * @Return:
 * @author: Heaven
 * @date: 2021/8/10 20:23
*/
@EnableScheduling
@Configuration
public class TimeUtil {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Scheduled(cron = "0 */5 * * * ?")
    public void Synchronize(){
        List<BlogInfo> blogInfos = redisUtil.getAllHashValue("views");
        for (BlogInfo blogInfo: blogInfos){
            blogMapper.update(null,new UpdateWrapper<BlogInfo>().set("views",blogInfo.getViews()).eq("id",blogInfo.getId()));
            redisUtil.deleteHashValue("views",String.valueOf(blogInfo.getId()));
        }
    }
}
