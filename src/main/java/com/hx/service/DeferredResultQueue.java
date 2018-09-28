package com.hx.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author yangxinqiang
 * @create 2018-09-28 16:52
 */
public class DeferredResultQueue {

    //模拟的消息队列
    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<DeferredResult<Object>>();

    //加入到消息队列
    public static void save(DeferredResult<Object> deferredResult){
        System.out.println("save 的  "+Thread.currentThread());
        queue.add(deferredResult);
    }

    //获取消息队列的对象，并从消息队列中移除
    public static DeferredResult<Object> get(){
        System.out.println("get 的  "+Thread.currentThread());
        return queue.poll();
    }
}
