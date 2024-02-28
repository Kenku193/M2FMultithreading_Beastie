package org.example.locks.example1;

public class MyThread implements Runnable{
    @Override
    public void run() {
        App.lock.lock();
        try {
            System.out.println(getUpdatedInt());
        }
        finally {
            App.lock.unlock();
        }
    }

    Integer getUpdatedInt(){
        return App.i++;
    }
}
