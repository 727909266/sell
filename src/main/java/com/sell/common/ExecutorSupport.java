package com.sell.common;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
public class ExecutorSupport {
    public static void executeUntil(Executor executor, List<Runnable> runnables, long waitTime, TimeUnit unit) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(runnables.size());
        runnables.forEach(runnable -> {
            executor.execute(() -> {
                try {
                    runnable.run();
                } finally{
                    countDownLatch.countDown();
                }
            });
        });


        //捕获到InterruptedException异常后恢复中断状态
        /*
        如果此时你只是记录日志的话，那么就是一个不负责任的做法，因为在捕获InterruptedException异常的时候自动的将是否请求中断标志置为了false。
        至少在捕获了InterruptedException异常之后，如果你什么也不想做，那么就将标志重新置为true，以便栈中更高层的代码能知道中断，并且对中断作出响应。

        catch (InterruptedException e) {
             // Restore the interrupted status
             Thread.currentThread().interrupt();
         }
         */

        countDownLatch.await(waitTime, unit);
    }
}
