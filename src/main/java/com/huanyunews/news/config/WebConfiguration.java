package com.huanyunews.news.config;

import com.huanyunews.news.interceptor.ArticleClickInterceptor;
import com.huanyunews.news.interceptor.LoginStateInterceptor;
import com.huanyunews.news.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private PassportInterceptor passportInterceptor;
    @Autowired
    private ArticleClickInterceptor articleClickInterceptor;
    @Autowired
    private LoginStateInterceptor loginStateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(articleClickInterceptor).addPathPatterns("/article/*");
        registry.addInterceptor(loginStateInterceptor).addPathPatterns("/note");
        super.addInterceptors(registry);
    }
}
