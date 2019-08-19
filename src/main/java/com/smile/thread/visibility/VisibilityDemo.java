package com.smile.thread.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 黄文海多线程编程，清单2.7中示例
 * @author: ayuan
 * @create: 2019-04-26 9:54
 */
public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        //Thread thread = new Thread(new TimeConsumingTask());
        Thread thread = new Thread(timeConsumingTask);
        thread.start();
        // 指定的时间内任务没有执行结束的话，就将其取消
        Thread.sleep(10000);
        timeConsumingTask.cancel();
    }
}

class TimeConsumingTask implements Runnable {
    private boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;
            }
        }
        if (toCancel) {
            System.out.println(" Task was canceled.");
        } else {
            System.out.println(" Task done.");
        }
    }

    private boolean doExecute() {
        boolean isDone = false;
        System.out.println(" executing...");
        // 模拟 实际 操作 的 时间 消耗
        //Tools.randomPause(50);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 省略 其他 代码
        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + " canceled.");
    }
}
