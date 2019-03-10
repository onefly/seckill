package com.seckill.common.task;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEventProducer<T> {

    private final RingBuffer<GenericEvent<T>> ringBuffer;

    public GenericEventProducer(RingBuffer<GenericEvent<T>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(T data) {
        // Grab the next sequence 获取下一个sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor 获取entry对象
            GenericEvent<T> event = ringBuffer.get(sequence);
            // for the sequence 对应sequence位置上的event
            // Fill with data 填充业务数据
            event.set(data);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
