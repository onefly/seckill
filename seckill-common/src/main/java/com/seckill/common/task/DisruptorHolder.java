package com.seckill.common.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class DisruptorHolder<T> {

    private int bufferSize = 1024;

    private GenericEventFactory<T> eventFactory = new GenericEventFactory<T>();

    private ThreadFactory threadFactory = Executors.defaultThreadFactory();


    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public GenericEventFactory<T> getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(GenericEventFactory<T> eventFactory) {
        this.eventFactory = eventFactory;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }
}
