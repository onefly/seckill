package com.seckill.service;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.seckill.common.task.DisruptorHolder;
import com.seckill.common.task.GenericEvent;
import com.seckill.common.task.GenericEventProducer;
import com.seckill.common.task.GenericWorkHandler;
import com.seckill.entity.OrderBase;
import com.seckill.entity.TaskStatus;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/3/10.
 */
@Service
public class OrderBaseWorker {
    private volatile boolean runging = false;
    private Disruptor<GenericEvent<OrderBase>> disruptor;
    @Autowired
    private OrderBaseService orderBaseService;
    @PostConstruct
    public void start(){
        runging = true;
        DisruptorHolder<OrderBase> disruptorHolder = new DisruptorHolder<OrderBase>();
        disruptor = new Disruptor(disruptorHolder.getEventFactory(), disruptorHolder.getBufferSize(),
                disruptorHolder.getThreadFactory());

        addHandler(disruptor);

        disruptor.start();

        RingBuffer<GenericEvent<OrderBase>> ringBuffer = disruptor.getRingBuffer();

        doAfterDisruptorStart(ringBuffer);

        GenericEventProducer<OrderBase> producer = new GenericEventProducer(ringBuffer);
        produceData(producer);

    }

    private void produceData(GenericEventProducer<OrderBase> producer) {
        String ip = "192.168.21.99";
        while (runging) {
            orderBaseService.updateWorkerIpByStatus(ip, TaskStatus.WAIT_RUN.getValue());
            List<OrderBase> list = orderBaseService.getOrderBaseList(ip,TaskStatus.WAIT_RUN.getValue());
            for(OrderBase orderBase : list) {
                producer.onData(orderBase);
                orderBaseService.updateStatusById(orderBase.getId(),TaskStatus.RUNNING.getValue());
            }
            if(CollectionUtils.isEmpty(list) || list.size() < 1024){
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PreDestroy
    public void stop(){
        runging = false;
        if(disruptor != null){
            disruptor.shutdown();
        }
    }
    private void doAfterDisruptorStart(RingBuffer<GenericEvent<OrderBase>> ringBuffer) {
        System.out.println("start worker success ######################");
    }

    private void addHandler(Disruptor<GenericEvent<OrderBase>> disruptor) {
        disruptor.handleEventsWithWorkerPool(orderBaseGenericEvent -> {
            OrderBase orderBase = orderBaseGenericEvent.get();
            System.out.println(" worker receive ######################"+orderBase);
            orderBaseService.updateStatusById(orderBase.getId(),TaskStatus.RUN_SUCCESS.getValue());
        });
    }
}
