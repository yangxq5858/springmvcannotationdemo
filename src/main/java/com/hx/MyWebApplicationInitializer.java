package com.hx;

import com.hx.config.AppConfig;
import com.hx.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author yangxinqiang
 * @create 2018-09-27 17:01
 * Web容器启动的时候，会创建对象，调用方法来初始化前端控制器
 */
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器的配置类，相当于Spring的配置文件，父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //获取Web容器的配置类，相当于SpringMVC的配置文件，子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    //获取DispatcherServlet的映射信息

    //  /表示 拦截所有请求（包括静态资源js，jpg，png，但不包括jsp文件）
    //  /*   表示连jsp文件都会拦截，jsp页面是Tomcat的jsp引擎解析的，故不能拦截掉
    @Override
    protected String[] getServletMappings() {
        //拦截除了jsp页面外的所有请求
        return new String[]{"/"};
    }
}
