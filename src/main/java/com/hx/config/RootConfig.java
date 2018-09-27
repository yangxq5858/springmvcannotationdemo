package com.hx.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author yangxinqiang
 * @create 2018-09-27 17:11
 *
 * 父容器配置
 */

//排除controller 注意，这里不能加useDefaultFilters = false 这个属性，否则，HelloService无法加入到容器中
@ComponentScan(value = "com.hx",excludeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
