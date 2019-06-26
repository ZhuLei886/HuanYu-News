package com.huanyunews.news.interceptor;

import com.huanyunews.news.redis.JedisService;
import com.huanyunews.news.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tuzhenyu on 17-7-20.
 * @author tuzhenyu
 */
@Component
public class ArticleClickInterceptor implements HandlerInterceptor {
    @Autowired
    private JedisService jedisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //取第三个元素
        String uri = httpServletRequest.getServletPath().split("/")[2];
        //截取到正在访问的url
        String uriKey = RedisKeyUtil.getClickCountKey(uri);
        //给拦截到的连接加上增量
        jedisService.zincrby("hotArticles",uriKey);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
