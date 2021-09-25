package com.heaven.handler;

import com.heaven.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorHandle implements HandlerInterceptor{
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("X-Real-Ip");
        Long count = null;
        if (redisUtil.hasKey(ip)) {
            count = redisUtil.subKey(ip);
        }
        else {
            redisUtil.setTex(ip, 300);
            count = Long.valueOf(5);
        }
        if ( count < 0){
            request.getRequestDispatcher("/flushQuickError").forward(request,response);
            return false;
        }
        else
            return true;
    }
}
