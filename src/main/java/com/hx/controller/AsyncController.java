package com.hx.controller;

import com.hx.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author yangxinqiang
 * @create 2018-09-28 14:49
 */
@Controller
public class AsyncController {


    /**
     * 返回Callable时
     * 1. SpringMVC就会启动一个新线程 TaskExecutor进行处理
     * 2. DispatcherServlet和所有的Filter退出Spring的容器的线程，但Response的线程，还处于挂起状态
     * 3. Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理
     * 4. SpringMVC继续从 收请求-->视图渲染 来处理
     *
     * 以下是执行结果，我们可以看到 MyInterceptor...preHandle... 执行了2次，
     * MyFilter...doFilter...
     * * MyInterceptor...preHandle...public java.util.concurrent.Callable<java.lang.String> com.hx.controller.AsyncController.async01()
     * 主线程开始..Thread[http-apr-8080-exec-3,5,main]====1538122865548
     * 主线程结束..Thread[http-apr-8080-exec-3,5,main]====1538122865548
     * 副线程..Thread[MvcAsync2,5,main]====1538122865548
     * MyFilter...doFilter...
     * MyInterceptor...preHandle...public java.util.concurrent.Callable<java.lang.String> com.hx.controller.AsyncController.async01()
     * postHandle...
     * afterCompletion...
     *
     * @return
     */

    @ResponseBody //表示将数据直接返回到前端
    @RequestMapping("/async01")
    public Callable<String> async01(){
        System.out.println("主线程开始.."+Thread.currentThread()+"===="+System.currentTimeMillis());
        Callable<String> stringCallable = new Callable<String>() {
            public String call() throws Exception {
                System.out.println("副线程.."+Thread.currentThread()+"===="+System.currentTimeMillis());
                return "call async01";
            }
        };
        System.out.println("主线程结束.."+Thread.currentThread()+"===="+System.currentTimeMillis());
        return stringCallable;
    }

    /**
     * 模拟创建订单
     * @return
     */

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        System.out.println("request处理之前 "+ Thread.currentThread());
        //表示创建订单超时时间为3秒，3秒未成功，返回错误消息
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long) 3000,"create order fail...");
        //保存deferredResult到消息队列中,等待被处理（就是必须有另外一个线程去setResult,setResult时，就会通知数据处理完成了。）
        DeferredResultQueue.save(deferredResult);


        System.out.println("Return 之前  "+Thread.currentThread());
        return deferredResult;
    }


    @ResponseBody
    @RequestMapping("/create")
    public String create(){

        System.out.println("create 的  "+Thread.currentThread());
        //创建订单
        String orderId = UUID.randomUUID().toString();
        DeferredResult<Object> objectDeferredResult = DeferredResultQueue.get();
        objectDeferredResult.setResult(orderId);

        return orderId+"--Created...";
    }


}
