package com.seckill.common.task;

import com.lmax.disruptor.WorkHandler;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikun on 2017/2/17.
 */
public class GenericWorkHandler<T> implements WorkHandler<GenericEvent<T>> {
    private String name;

    public GenericWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(GenericEvent<T> event) throws Exception {
        System.out.println("消费者workHandler(" + name + ")" + ":" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }
}
