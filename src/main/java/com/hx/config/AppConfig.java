package com.hx.config;

        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.FilterType;
        import org.springframework.core.type.filter.TypeFilter;
        import org.springframework.stereotype.Controller;

        import java.lang.reflect.Type;

/**
 * @author yangxinqiang
 * @create 2018-09-27 17:11
 * 子容器配置
 */

//SpringMVC 只扫描Controller，子容器
//useDefaultFilters = false；includeFilters 这种过滤器，必须让禁用默认的过滤器，才生效
@ComponentScan(value = "com.hx",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})

},useDefaultFilters = false)
public class AppConfig {
}
