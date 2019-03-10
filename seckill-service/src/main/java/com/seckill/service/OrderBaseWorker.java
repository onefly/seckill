package com.seckill.service;

import com.lmax.disruptor.ExceptionHandler;
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
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/3/10.
 */
@Service
public class OrderBaseWorker {
    private int customerThreadSize = 8;
    private int produceThreadSize = 2;
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
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(produceThreadSize,
                new BasicThreadFactory.Builder().namingPattern("produceQueue-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() ->
        {
            String ip= "127.0.0.1";
            orderBaseService.updateWorkerIpByStatus(ip, TaskStatus.WAIT_RUN.getValue());
            List<OrderBase> list = orderBaseService.getOrderBaseList(ip,TaskStatus.WAIT_RUN.getValue());
            for(OrderBase orderBase : list) {
                producer.onData(orderBase);
                orderBaseService.updateStatusById(orderBase.getId(),TaskStatus.RUNNING.getValue());
            }
        },1000,1000, TimeUnit.MILLISECONDS);
        System.out.println("produce date start @@@@@@@@@@@@@@@@@@@@@@@@@@ ");
    }

    @PreDestroy
    public void stop(){
        runging = false;
        if(disruptor != null){
            disruptor.shutdown();
        }
    }
    private void doAfterDisruptorStart(RingBuffer<GenericEvent<OrderBase>> ringBuffer) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("remainingCapacity-monitoring-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> System.out.println("remainingCapacity=========:" + ringBuffer.remainingCapacity()),1,1, TimeUnit.MINUTES);
        System.out.println("start worker success ######################");
    }

    private void addHandler(Disruptor<GenericEvent<OrderBase>> disruptor) {
        WorkHandler<GenericEvent<OrderBase>>[] workHandlers = new WorkHandler[customerThreadSize];
        for(int i=0;i< customerThreadSize ;i++){
            workHandlers[i] = orderBaseGenericEvent -> {
                OrderBase orderBase = orderBaseGenericEvent.get();
                System.out.println(" worker receive ######################"+orderBase);
                Assert.notNull(orderBase.getJson(), "业务数据json is null");
                orderBaseService.updateStatusById(orderBase.getId(),TaskStatus.RUN_SUCCESS.getValue());
            };
        }
        disruptor.handleEventsWithWorkerPool(workHandlers);

        //异常处理
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<GenericEvent<OrderBase>>() {
            @Override
            public void handleEventException(Throwable throwable, long l, GenericEvent<OrderBase> orderBaseGenericEvent) {
                OrderBase orderBase = orderBaseGenericEvent.get();
                System.out.print("执行异常："+throwable.getMessage());
                orderBaseService.updateStatusById(orderBase.getId(),TaskStatus.WAIT_RETRY.getValue());
            }

            @Override
            public void handleOnStartException(Throwable throwable) {

            }

            @Override
            public void handleOnShutdownException(Throwable throwable) {

            }
        });
    }
}
