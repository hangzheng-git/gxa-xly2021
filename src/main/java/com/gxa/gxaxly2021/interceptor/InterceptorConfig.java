package com.gxa.gxaxly2021.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] urls = {"/login/**", "/backend/**","/empl/changePwd/**"};
        registry.addInterceptor(new LoginInterceptor())
                // 拦截所有的路径
                .addPathPatterns("/**")
                //放开的路径
                .excludePathPatterns(urls);
    }
}

