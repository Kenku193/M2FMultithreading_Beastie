package org.example.airport.service;

import org.example.airport.repository.AircraftRepository;

import java.util.concurrent.locks.Lock;

public class AircraftLander implements Runnable {

    private Lock lock;
    private AircraftRepository aircraftsRepository;

    public AircraftLander(Lock lock, AircraftRepository aircraftsRepository) {
        this.lock = lock;
        this.aircraftsRepository = aircraftsRepository;
    }

//    @Override
//    public void run() {
//        lock.lock();
//        try {
//            while (true) {
//                Thread.sleep(700);
//                if(aircraftsRepository.aircraftsDeque.poll().landing()){
//                    System.out.println("Количество единиц в небе: " + aircraftsRepository.aircraftsDeque.size());
//                }
//                else {
//                    System.out.println("Что-то идет не так");
//                }
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            lock.unlock();
//        }
//    }

    @Override
    public void run() {
        lock.lock();
        try {
            if (aircraftsRepository.aircraftsDeque.pollFirst().landing()) {
                System.out.println("Посадка окончена! Количество единиц в небе: " + aircraftsRepository.aircraftsDeque.size());
            } else {
                System.out.println("Что-то идет не так");
            }
        } finally {
            lock.unlock();
        }
    }
}
