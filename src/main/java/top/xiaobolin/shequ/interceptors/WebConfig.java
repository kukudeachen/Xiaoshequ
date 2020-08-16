package top.xiaobolin.shequ.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

/**
 * @author：xiaobolin
 * @date 2020/7/28/0028 - 19:31
 */
//拦截器！！
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SesstionInterceptor sesstionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //引用一个新创建的拦截器并拦截所有页面/**
        registry.addInterceptor(sesstionInterceptor).addPathPatterns("/**");
    }
}
