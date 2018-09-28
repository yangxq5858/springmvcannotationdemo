package com.hx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author yangxinqiang
 * @create 2018-09-27 17:11
 *
 * 父容器配置
 */


//排除controller之外的，注意这里不能禁用默认的过滤器
@ComponentScan(value = "com.hx",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
