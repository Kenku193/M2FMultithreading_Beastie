package org.example.locks.example2;

import java.util.concurrent.locks.ReentrantLock;

public class Program {

    public static void main(String[] args) {

        CommonResource commonResource= new CommonResource();
        ReentrantLock lock = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread(commonResource, lock));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}


