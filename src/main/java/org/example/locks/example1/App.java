package org.example.locks.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static Lock lock = new ReentrantLock();
    public static Integer i = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<MyThread> myThreadList = new ArrayList<>();
        for (int j = 0; j < 20; j++) {
            myThreadList.add(new MyThread());
        }
        for (MyThread myThread : myThreadList) {
            executorService.submit(myThread);
        }
        executorService.shutdown();
    }
}
