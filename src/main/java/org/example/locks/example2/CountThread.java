package org.example.locks.example2;

import java.util.concurrent.locks.ReentrantLock;

class CountThread implements Runnable{

    CommonResource res;
    ReentrantLock lock;
    CountThread(CommonResource res, ReentrantLock lock){
        this.res=res;
        this.lock = lock;
    }
    public void run(){

        lock.lock(); // устанавливаем блокировку
        try{
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            lock.unlock(); // снимаем блокировку
        }
    }
}
