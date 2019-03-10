package com.seckill.common.task;

import com.lmax.disruptor.EventFactory;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEventFactory<T> implements EventFactory<GenericEvent<T>> {

    @Override
    public GenericEvent<T> newInstance() {
        return new GenericEvent();
    }
}
