package thread;

import java.util.concurrent.*;

public class BlockQueue {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);


//        System.out.println(blockingQueue.add(1));
//        System.out.println(blockingQueue.add(2));
//        System.out.println(blockingQueue.add(3));
//        System.out.println(blockingQueue);
////        System.out.println(blockingQueue.add(4));
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
////        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.element());

//        System.out.println(blockingQueue.offer(1));
//        System.out.println(blockingQueue.offer(2));
//        System.out.println(blockingQueue.offer(3));
//        System.out.println(blockingQueue.offer(4));

//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.peek());

//        blockingQueue.put(1);
//        blockingQueue.put(2);
//        blockingQueue.put(3);
//        System.out.println(blockingQueue);
//
//        blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();

        blockingQueue.offer(1,2, TimeUnit.SECONDS);
        blockingQueue.offer(2,2, TimeUnit.SECONDS);
        blockingQueue.offer(3,2, TimeUnit.SECONDS);
        System.out.println(blockingQueue);
        blockingQueue.offer(4,4, TimeUnit.SECONDS);  //写堵塞 最多等待4s
        System.out.println(blockingQueue);
//
//        System.out.println("写堵塞 等待完毕");
//
//        blockingQueue.poll();
//        blockingQueue.poll();
//        blockingQueue.poll();
//        blockingQueue.poll(2,TimeUnit.SECONDS); //读堵塞 最多等待2秒
//
//        System.out.println("读堵塞 等待完毕");




    }
}
