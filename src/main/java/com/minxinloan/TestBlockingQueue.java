package com.minxinloan;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017-8-9.
 */


public class TestBlockingQueue {
    public static class Producer implements Runnable{
        private BlockingQueue<Integer> blockingQueue;
        private Boolean flag;
        private Random random;

        public Producer(BlockingQueue blockingQueue){
            this.blockingQueue = blockingQueue;
            this.random = new Random();
            flag = false;
        }

        public void run(){
            while(!flag){
                int product = random.nextInt(100);
                try {
                    blockingQueue.put(product);
                    System.out.println(Thread.currentThread().getName()+":::"+product);
                    Thread.sleep(40);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }

        public void shutdown(){
            flag = true;
        }
    }

    public static class Consumer implements Runnable{
        private BlockingQueue<Integer> blockingQueue;
        private boolean flag;//成员变量会给默认值false

        public Consumer(BlockingQueue<Integer> blockingQueue){
            this.blockingQueue = blockingQueue;
        }
        public void run(){
            while(!flag){
                try{
                    Integer product =  blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()+"::::"+product);
                    Thread.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        public void shutdown(){
            flag = true;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        for(int i=0;i<10;i++){
            if(i<5){
                new Thread(producer,"producer"+i).start();
            }else{
                new Thread(consumer,"consumer"+(i-5)).start();
            }
        }

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        producer.shutdown();
        consumer.shutdown();
    }
}
