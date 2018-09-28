package com.hx.config;


import com.hx.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;


/**
 * @author yangxinqiang
 * @create 2018-09-27 17:11
 * 子容器配置
 */

//SpringMVC 只扫描Controller，子容器
//useDefaultFilters = false；includeFilters 这种过滤器，必须让禁用默认的过滤器，才生效
@ComponentScan(value = "com.hx",includeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = {Controller.class})

},useDefaultFilters = false)
@EnableWebMvc //开启SpringMVC支持
public class AppConfig extends WebMvcConfigurerAdapter {

   //只需要配置我们需要的部分


    //配置视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    //表示开启静态资源访问，否则，会被拦截
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        //拦截任意多路径的请求
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }



}
