package com.huanyunews.news.interceptor;


import com.huanyunews.news.redis.JedisService;
import com.huanyunews.news.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisService jedisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        String uri = httpServletRequest.getServletPath();
        String uriKey = RedisKeyUtil.getClickCountKey(uri);
        jedisService.incr(uriKey);
        return true;
    }
}
